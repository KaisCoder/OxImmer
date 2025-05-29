package cn.kais.immer.rom

import android.content.Context
import android.provider.Settings

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : 869649338@qq.com
 * @Describe : 华为 rom
 */
internal class EmuiRom : BaseRom() {

    override fun fullScreenGestureOn(context: Context): Boolean {
        return Settings.Global.getInt(context.contentResolver, "navigationbar_is_min", -1) > 0
    }

}