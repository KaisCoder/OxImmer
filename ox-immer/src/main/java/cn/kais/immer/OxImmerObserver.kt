package cn.kais.immer

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 观察者
 */
class OxImmerObserver(private var only: Boolean) : DefaultLifecycleObserver {

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        OxImmerController.instance.removeAllData(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (owner !is Fragment) return
        if (only) {
            owner.resetStatusBarOnlyLight()
            return
        }
        owner.resetLight()
    }

    private fun Fragment.resetStatusBarOnlyLight() {
        val staDefault = OxImmerController.instance.getStatusBarDefault(this)
        if (staDefault) {
            val staConfig = statusBarConfig
            val parentStaConfig = requireActivity().statusBarConfig
            if (staConfig.light != parentStaConfig.light) {
                getStatusBarOnly()
            }
        }
    }

    private fun Fragment.resetLight() {
        val staDefault = OxImmerController.instance.getStatusBarDefault(this)
        val navDefault = OxImmerController.instance.getNavigationBarDefault(this)
        if (staDefault) {
            val staConfig = statusBarConfig
            val parentStaConfig = requireActivity().statusBarConfig
            if (staConfig.light != parentStaConfig.light) {
                getStatusBar()
            }
        }
        if (navDefault) {
            val navConfig = navigationBarConfig
            val parentNavConfig = requireActivity().navigationBarConfig
            if (navConfig.light != parentNavConfig.light) {
                getNavigationBar()
            }
        }
    }

}