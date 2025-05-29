package cn.kais.immer.core

import android.graphics.Rect
import android.view.View
import cn.kais.immer.R

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/8/17  7:45 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
fun View.onKeyboardOpen(block: ((height: Int) -> Unit)? = null) {
    addKeyboardListener()
    openKeyboardListener = block
}

fun View.onKeyboardClose(block: ((originHeight: Int) -> Unit)? = null) {
    addKeyboardListener()
    closeKeyboardListener = block
}

private fun View.addKeyboardListener() {
    if (getTag(R.id.kbl_keyboard_listener) == true) {
        return
    }
    val vh = height
    if (vh > 0) {
        originHeight = vh
        originVisibleHeight = vh
    }
    viewTreeObserver.addOnGlobalLayoutListener {
        if (originHeight < 0) {
            originHeight = height
        }
        val h = _visibleHeight
        if (originVisibleHeight < 0) {
            originVisibleHeight = h
        }
        val diff = originVisibleHeight - h
        if (!keyboardOpened && diff > 100.dp) {
            originHeight = height
            openKeyboardListener?.invoke(diff)
            keyboardOpened = true
        }
        if (keyboardOpened && visibleHeight > 0 && h - visibleHeight > 100.dp) {
            closeKeyboardListener?.invoke(originHeight)
            keyboardOpened = false
        }
        visibleHeight = h
    }
    setTag(R.id.kbl_keyboard_listener, true)
}

private var View.openKeyboardListener: ((height: Int) -> Unit)?
    get() = getTag(R.id.kbl_open_keyboard) as ((Int) -> Unit)?
    set(value) {
        setTag(R.id.kbl_open_keyboard, value)
    }

private var View.closeKeyboardListener: ((originHeight: Int) -> Unit)?
    get() = getTag(R.id.kbl_close_keyboard) as ((Int) -> Unit)?
    set(value) {
        setTag(R.id.kbl_close_keyboard, value)
    }

private var View.originHeight: Int
    get() {
        val h = getTag(R.id.kbl_origin_height)
        if (h is Int) {
            return h
        }
        return -1
    }
    set(value) {
        setTag(R.id.kbl_origin_height, value)
    }

private var View.originVisibleHeight: Int
    get() {
        val h = getTag(R.id.kbl_origin_visible_height)
        if (h is Int) {
            return h
        }
        return -1
    }
    set(value) {
        setTag(R.id.kbl_origin_visible_height, value)
    }

private var View.visibleHeight: Int
    get() {
        val h = getTag(R.id.kbl_visible_height)
        if (h is Int) {
            return h
        }
        return -1
    }
    set(value) {
        setTag(R.id.kbl_visible_height, value)
    }

private var View.keyboardOpened: Boolean
    get() = getTag(R.id.kbl_keyboard_opened) == true
    set(value) {
        setTag(R.id.kbl_keyboard_opened, value)
    }

private val View._visibleHeight: Int
    get() {
        val r = Rect()
        getWindowVisibleDisplayFrame(r)
        return r.height()
    }