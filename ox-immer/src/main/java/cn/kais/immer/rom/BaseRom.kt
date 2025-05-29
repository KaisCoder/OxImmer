package cn.kais.immer.rom

import android.content.Context
import cn.kais.immer.extension.commonNavigationBarExist

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/7  4:56 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
abstract class BaseRom : Rom {

    override fun navigationBarExist(context: Context): Boolean {
        if (fullScreenGestureOn(context)) {
            if (screenIndicatorOn(context)) {
                return true
            }
            return false
        }
        return context.commonNavigationBarExist()
    }

    // 是否开启了全面屏手势
    protected abstract fun fullScreenGestureOn(context: Context): Boolean

    // 是否开启了手势提示线
    protected open fun screenIndicatorOn(context: Context): Boolean = false

}