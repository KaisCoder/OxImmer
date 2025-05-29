package cn.kais.immer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import cn.kais.immer.bean.BarConfig
import cn.kais.immer.core.addObserver
import cn.kais.immer.core.defaultNavigationBar
import cn.kais.immer.core.defaultStatusBar
import cn.kais.immer.core.statusBarOnlyInitialization
import cn.kais.immer.core.ultimateBarXInitialization
import cn.kais.immer.core.updateNavigationBar
import cn.kais.immer.core.updateStatusBar
import cn.kais.immer.extension.setStatusBarSystemUiFlagWithLight
import cn.kais.immer.extension.setSystemUiFlagWithLight

/**
 * 操作者
 */
internal fun FragmentActivity.applyStatusBar(config: BarConfig) {
    ultimateBarXInitialization()
    val navLight = controller.getNavigationBarConfig(this).light
    setSystemUiFlagWithLight(config.light, navLight)
    updateStatusBar(config)
    defaultNavigationBar()
    addObserver()
}

internal fun FragmentActivity.applyNavigationBar(config: BarConfig) {
    ultimateBarXInitialization()
    val staLight = controller.getStatusBarConfig(this).light
    setSystemUiFlagWithLight(staLight, config.light)
    updateNavigationBar(config)
    defaultStatusBar()
    addObserver()
}

internal fun Fragment.applyStatusBar(config: BarConfig) {
    requireActivity().ultimateBarXInitialization()
    ultimateBarXInitialization()
    val navLight = controller.getNavigationBarConfig(this).light
    requireActivity().setSystemUiFlagWithLight(config.light, navLight)
    updateStatusBar(config)
    requireActivity().defaultNavigationBar()
    addObserver()
    requireActivity().addObserver()
}

internal fun Fragment.applyNavigationBar(config: BarConfig) {
    requireActivity().ultimateBarXInitialization()
    ultimateBarXInitialization()
    val staLight = controller.getStatusBarConfig(this).light
    requireActivity().setSystemUiFlagWithLight(staLight, config.light)
    updateNavigationBar(config)
    requireActivity().defaultStatusBar()
    addObserver()
    requireActivity().addObserver()
}

internal fun FragmentActivity.applyStatusBarOnly(config: BarConfig) {
    statusBarOnlyInitialization()
    setStatusBarSystemUiFlagWithLight(config.light)
    updateStatusBar(config)
    addObserver(true)
}

internal fun Fragment.applyStatusBarOnly(config: BarConfig) {
    requireActivity().statusBarOnlyInitialization()
    statusBarOnlyInitialization()
    requireActivity().setStatusBarSystemUiFlagWithLight(config.light)
    updateStatusBar(config)
    addObserver(true)
    requireActivity().addObserver(true)
}