package cn.kais.immer.rom

import android.content.Context
import android.provider.Settings

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : 869649338@qq.com
 * @Describe : vivo rom
 */
internal class FuntouchRom : BaseRom() {

    override fun fullScreenGestureOn(context: Context): Boolean {
        return Settings.Secure.getInt(context.contentResolver, "navigation_gesture_on", -1) > 0
    }

}