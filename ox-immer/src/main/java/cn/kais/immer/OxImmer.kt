package cn.kais.immer

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import cn.kais.immer.bean.BarConfig
import cn.kais.immer.core.addNavigationBarBottomPadding
import cn.kais.immer.core.addStatusBarTopPadding
import cn.kais.immer.extension.navigationBarHeight
import cn.kais.immer.extension.statusBarHeight

fun FragmentActivity.statusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(BarConfig.newInstance(), block)

fun FragmentActivity.navigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(BarConfig.newInstance(), block)

fun FragmentActivity.statusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(BarConfig.newInstance(), block)

fun FragmentActivity.getStatusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(statusBarConfig, block)

fun FragmentActivity.getNavigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(navigationBarConfig, block)

fun FragmentActivity.getStatusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(statusBarConfig, block)

fun Fragment.statusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(BarConfig.newInstance(), block)

fun Fragment.navigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(BarConfig.newInstance(), block)

fun Fragment.statusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(BarConfig.newInstance(), block)

fun Fragment.getStatusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(statusBarConfig, block)

fun Fragment.getNavigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(navigationBarConfig, block)

fun Fragment.getStatusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(statusBarConfig, block)

val FragmentActivity.statusBarConfig: BarConfig
    get() = controller.getStatusBarConfig(this)

val FragmentActivity.navigationBarConfig: BarConfig
    get() = controller.getNavigationBarConfig(this)

val Fragment.statusBarConfig: BarConfig
    get() = controller.getStatusBarConfig(this)

val Fragment.navigationBarConfig: BarConfig
    get() = controller.getNavigationBarConfig(this)

val statusBarHeight: Int
    get() = controller.context.statusBarHeight

val navigationBarHeight: Int
    get() {
        val rom = controller.rom
        val context = controller.context
        if (!rom.navigationBarExist(context)) return 0
        return controller.context.navigationBarHeight
    }

fun View.addStatusBarTopPadding() {
    addStatusBarTopPadding()
}

fun View.addNavigationBarBottomPadding() {
    addNavigationBarBottomPadding()
}

internal fun FragmentActivity.statusBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

internal fun FragmentActivity.navigationBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

internal fun FragmentActivity.statusBarOnly(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (block != null) {
        config.block()
    }
    applyStatusBarOnly(config)
}

internal fun Fragment.statusBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

internal fun Fragment.navigationBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

internal fun Fragment.statusBarOnly(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (block != null) {
        config.block()
    }
    applyStatusBarOnly(config)
}