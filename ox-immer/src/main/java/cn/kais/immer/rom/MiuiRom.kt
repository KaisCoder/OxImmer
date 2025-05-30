package cn.kais.immer.rom

import android.content.Context
import android.provider.Settings
import cn.kais.immer.extension.navigationBarHeight
import cn.kais.immer.extension.screenHeight

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:09 PM
 * @email    : 869649338@qq.com
 * @Describe : 小米 rom
 */
internal class MiuiRom : BaseRom() {

    override fun fullScreenGestureOn(context: Context): Boolean {
        return Settings.Global.getInt(context.contentResolver, "force_fsg_nav_bar", -1) > 0
    }

    override fun screenIndicatorOn(context: Context): Boolean {
        val navHeight = context.navigationBarHeight
        val screenHeight = context.screenHeight
        // 当屏幕高度大于状态栏高度的 30 倍时，就认为开启了手势提示线
        // 否则认为没有开启手势提示线
        return navHeight > 0 && screenHeight / navHeight > 30
    }

}