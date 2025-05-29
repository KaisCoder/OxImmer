package cn.kais.immer.core

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import cn.kais.immer.BuildConfig
import cn.kais.immer.OxImmerObserver
import cn.kais.immer.R
import cn.kais.immer.bean.BarBackground
import cn.kais.immer.bean.BarConfig
import cn.kais.immer.controller
import cn.kais.immer.extension.barTransparent
import cn.kais.immer.extension.children
import cn.kais.immer.extension.contain
import cn.kais.immer.extension.contentView
import cn.kais.immer.extension.getColorInt
import cn.kais.immer.extension.landscape
import cn.kais.immer.extension.rootView
import cn.kais.immer.extension.statusBarTransparent
import cn.kais.immer.navigationBarHeight
import cn.kais.immer.statusBarHeight
import cn.kais.immer.view.ActivityTag
import cn.kais.immer.view.Creator
import cn.kais.immer.view.FragmentTag
import cn.kais.immer.view.FrameLayoutCreator
import cn.kais.immer.view.RelativeLayoutCreator
import cn.kais.immer.view.Tag
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 17:55
 * @email    : 869649338@qq.com
 * @Describe :
 */
private const val TAG_WRAPPER = "${BuildConfig.LIBRARY_PACKAGE_NAME}_fragment_wrapper"

internal fun FragmentActivity.ultimateBarXInitialization() {
    if (controller.getInitialization(this)) return
    controller.putOriginConfig(this)
    barInitialization()
    fixBottomNavigationViewPadding()
    addKeyboardListener()
    controller.putInitialization(this)
}

internal fun FragmentActivity.statusBarOnlyInitialization() {
    if (controller.getInitialization(this)) return
    controller.putOriginStatusBarConfig(this)
    statusBarInitialization()
    addKeyboardListener()
    controller.putInitialization(this)
}

internal fun Fragment.ultimateBarXInitialization() {
    if (controller.getInitialization(this)) return
    addFrameLayoutWrapper()
    val actStaConfig = controller.getStatusBarConfig(requireActivity())
    val staConfig = controller.getStatusBarConfig(this)
    staConfig.light = actStaConfig.light
    controller.putStatusBarConfig(this, staConfig)
    // 取 Activity 的 NavigationBarLight
    // 不能取 Activity 的 originColor 然后计算 light
    // 防止 Activity 之前设置了 light ，但是被通过 originColor 计算的 light 覆盖掉
//    manager.putNavigationBarLight(this, manager.getNavigationBarLight(requireActivity()))
    val actNavConfig = controller.getNavigationBarConfig(requireActivity())
    val navConfig = controller.getNavigationBarConfig(this)
    navConfig.light = actNavConfig.light
    controller.putNavigationBarConfig(this, navConfig)
    fixBottomNavigationViewPadding()
    controller.putInitialization(this)
}

internal fun Fragment.statusBarOnlyInitialization() {
    if (controller.getInitialization(this)) return
    addFrameLayoutWrapper()
    val actStaConfig = controller.getStatusBarConfig(requireActivity())
    val staConfig = controller.getStatusBarConfig(this)
    staConfig.light = actStaConfig.light
    controller.putStatusBarConfig(this, staConfig)
    controller.putInitialization(this)
}

internal fun FragmentActivity.updateStatusBar(config: BarConfig) {
    updateStatusBarView(config)
    controller.putStatusBarDefault(this)
    controller.putStatusBarConfig(this, config)
}

internal fun FragmentActivity.updateNavigationBar(config: BarConfig) {
    updateNavigationBarView(config)
    controller.putNavigationBarDefault(this)
    controller.putNavigationBarConfig(this, config)
}

internal fun Fragment.updateStatusBar(config: BarConfig) {
    val transparentConfig = BarConfig.newInstance().apply {
        transparent()
        light = config.light
    }
    requireActivity().updateStatusBar(transparentConfig)
    updateStatusBarView(config)
    controller.putStatusBarDefault(this)
    controller.putStatusBarConfig(this, config)
}

internal fun Fragment.updateNavigationBar(config: BarConfig) {
    val transparentConfig = BarConfig.newInstance().apply {
        transparent()
        light = config.light
    }
    requireActivity().updateNavigationBar(transparentConfig)
    updateNavigationBarView(config)
    controller.putNavigationBarDefault(this)
    controller.putNavigationBarConfig(this, config)
}

internal fun FragmentActivity.defaultStatusBar() {
    if (controller.getStatusBarDefault(this)) return
    updateStatusBar(controller.getStatusBarConfig(this))
}

internal fun FragmentActivity.defaultNavigationBar() {
    if (controller.getNavigationBarDefault(this)) return
    updateNavigationBar(controller.getNavigationBarConfig(this))
}

internal fun LifecycleOwner.addObserver(only: Boolean = false) {
    if (controller.getAddObserver(this)) return
//    lifecycle.addObserver(UltimateBarXObserver(only))
    lifecycle.addObserver(OxImmerObserver(only))
    controller.putAddObserver(this)
}


private fun FragmentActivity.barInitialization() {
    contentView?.clipToPadding = false
    rootView?.fitsSystemWindows = false
    barTransparent()
}

private fun FragmentActivity.statusBarInitialization() {
    contentView?.clipToPadding = false
    rootView?.fitsSystemWindows = false
    statusBarTransparent()
}

private fun FragmentActivity.updateStatusBarView(config: BarConfig) {
    contentView?.setStatusBarPadding(config.fitWindow)
    val landscape = controller.context.landscape
    val statusBar = contentView?.getCreator(ActivityTag.instance, landscape)?.getStatusBarView(this, config.fitWindow)
    statusBar?.updateBackground(config, Build.VERSION_CODES.M)
}

private fun FragmentActivity.updateNavigationBarView(config: BarConfig) {
    if (!controller.rom.navigationBarExist(this)) return
    val landscape = controller.context.landscape
    contentView?.setNavigationBarPadding(landscape, config.fitWindow)
    val navigationBar = contentView?.getCreator(ActivityTag.instance, landscape)?.getNavigationBarView(this, config.fitWindow)
    navigationBar?.updateBackground(config, Build.VERSION_CODES.O)
}

private fun Fragment.updateStatusBarView(config: BarConfig) {
    val rootView = addFrameLayoutWrapper()
    rootView.setStatusBarPadding(config.fitWindow)
    val landscape = controller.context.landscape
    val statusBar = rootView.getCreator(FragmentTag.instance, landscape)?.getStatusBarView(requireContext(), config.fitWindow)
    statusBar?.updateBackground(config, Build.VERSION_CODES.M)
}

private fun Fragment.updateNavigationBarView(config: BarConfig) {
    if (!controller.rom.navigationBarExist(requireActivity())) return
    val rootView = addFrameLayoutWrapper()
    val landscape = controller.context.landscape
    rootView.setNavigationBarPadding(landscape, config.fitWindow)
    val navigationBar = rootView.getCreator(FragmentTag.instance, landscape)?.getNavigationBarView(requireContext(), config.fitWindow)
    navigationBar?.updateBackground(config, Build.VERSION_CODES.O)
}

// 给 Fragment 的根 View 外面套一层 FrameLayout(用反射拿到根 View)
private fun Fragment.addFrameLayoutWrapper(): ViewGroup {
    val view = requireView()
    if (view is FrameLayout && view.tag == TAG_WRAPPER) {
        view.clipToPadding = false
        return view
    }
    val flWrapper = FrameLayout(requireContext())
    flWrapper.clipToPadding = false
    flWrapper.tag = TAG_WRAPPER
    flWrapper.setTag(R.id.fragment_container_view_tag, this)
    val parent = view.parent
    if (parent is ViewGroup) {
        val index = parent.indexOfChild(view)
        parent.removeViewAt(index)
        parent.addView(flWrapper, index)
    }
    flWrapper.addView(view)
    controller.fragmentViewFiled.set(this, flWrapper)
    return flWrapper
}

private fun ViewGroup.getCreator(tag: Tag, landscape: Boolean): Creator? {
    return when (this) {
        is FrameLayout -> FrameLayoutCreator(this, tag, landscape)
        is RelativeLayout -> RelativeLayoutCreator(this, tag, landscape)
        else -> null
    }
}

private fun ViewGroup.setStatusBarPadding(fitWindow: Boolean) {
    setPadding(
        paddingLeft,
        if (fitWindow) statusBarHeight else 0,
        paddingRight,
        paddingBottom
    )
}

private fun ViewGroup.setNavigationBarPadding(landscape: Boolean, fitWindow: Boolean) {
    if (landscape) {
        setPadding(
            paddingLeft,
            paddingTop,
            if (fitWindow) navigationBarHeight else 0,
            paddingBottom
        )
    } else {
        setPadding(
            paddingLeft,
            paddingTop,
            paddingRight,
            if (fitWindow) navigationBarHeight else 0
        )
    }
}

private fun View.updateBackground(config: BarConfig, endVersion: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        && Build.VERSION.SDK_INT < endVersion
        && config.light
        && updateBackground(config.lvlBackground)
    ) {
        return
    }
    updateBackground(config.background)
}

private fun View.updateBackground(background: BarBackground): Boolean {
    when {
        background.drawableRes > 0 -> {
            setBackgroundResource(background.drawableRes)
            return true
        }

        background.colorRes > 0 -> {
            setBackgroundColor(context.getColorInt(background.colorRes))
            return true
        }

        background.color > Color.BLACK - 1 -> {
            setBackgroundColor(background.color)
            return true
        }

        else -> setBackgroundColor(Color.TRANSPARENT)
    }
    return false
}

/**
 *  给 View 的顶部增加状态栏高度的 padding
 *  一般在状态栏透明且可被侵入的时候使用
 */
internal fun View.addStatusBarTopPadding() {
    setPadding(paddingLeft, paddingTop + statusBarHeight, paddingRight, paddingBottom)
    val lp = layoutParams
    when (this) {
        is Toolbar -> {
            when (lp.height) {
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT -> {
                    post {
                        lp.height = height + statusBarHeight
                        layoutParams = lp
                    }
                }

                else -> {
                    lp.height += statusBarHeight
                    layoutParams = lp
                }
            }
        }

        else -> {
            when (lp.height) {
                ViewGroup.LayoutParams.WRAP_CONTENT -> return
                ViewGroup.LayoutParams.MATCH_PARENT -> {
                    post {
                        lp.height = height + statusBarHeight
                        layoutParams = lp
                    }
                }

                else -> {
                    lp.height += statusBarHeight
                    layoutParams = lp
                }
            }
        }
    }
}

/**
 *  给 View 的底部增加导航栏高度的 padding
 *  一般在导航栏透明且可被侵入的时候使用
 */
internal fun View.addNavigationBarBottomPadding() {
    val ctx = context
    if (ctx is FragmentActivity && !controller.rom.navigationBarExist(ctx)) {
        return
    }
    setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom + navigationBarHeight)
    val lp = layoutParams
    when (this) {
        is Toolbar -> {
            when (lp.height) {
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT -> {
                    post {
                        lp.height = height + navigationBarHeight
                        layoutParams = lp
                    }
                }

                else -> {
                    lp.height += navigationBarHeight
                    layoutParams = lp
                }
            }
        }

        else -> {
            when (lp.height) {
                ViewGroup.LayoutParams.WRAP_CONTENT -> return
                ViewGroup.LayoutParams.MATCH_PARENT -> {
                    post {
                        lp.height = height + navigationBarHeight
                        layoutParams = lp
                    }
                }

                else -> {
                    lp.height += navigationBarHeight
                    layoutParams = lp
                }
            }
        }
    }
}

private fun FragmentActivity.fixBottomNavigationViewPadding() {
    rootView?.fixBottomNavigationViewPadding()
}

private fun Fragment.fixBottomNavigationViewPadding() {
    view?.fixBottomNavigationViewPadding()
}

private fun View.fixBottomNavigationViewPadding() {
    for (view in children) {
        if (view is BottomNavigationView) {
            val originBottomPadding = view.paddingBottom
            view.post { view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, originBottomPadding) }
        }
    }
}

internal fun FragmentActivity.addKeyboardListener() {
    rootView?.run {
        onKeyboardOpen {
            if (window?.attributes?.softInputMode?.contain(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) == true) {
                val lp = layoutParams ?: return@onKeyboardOpen
                lp.height = height - it
                layoutParams = lp
            }
        }
        onKeyboardClose {
            if (window?.attributes?.softInputMode?.contain(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) == true) {
                val lp = layoutParams ?: return@onKeyboardClose
                lp.height = it
                layoutParams = lp
            }
        }
    }
}