package cn.kais.immer.rom

import android.content.Context

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:01 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class OtherRom : BaseRom() {

    override fun fullScreenGestureOn(context: Context): Boolean = false

}