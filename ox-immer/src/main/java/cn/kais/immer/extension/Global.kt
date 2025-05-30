package cn.kais.immer.extension

import android.text.TextUtils
import cn.kais.immer.rom.EmuiRom
import cn.kais.immer.rom.FuntouchRom
import cn.kais.immer.rom.MiuiRom
import cn.kais.immer.rom.OtherRom
import cn.kais.immer.rom.Rom
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:00 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */

internal fun getRom(): Rom {
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_MIUI)))
        return MiuiRom()
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_EMUI)))
        return EmuiRom()
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_VIVO)))
        return FuntouchRom()
    return OtherRom()
}

private fun getProp(name: String): String? {
    val line: String?
    var input: BufferedReader? = null
    try {
        val p = Runtime.getRuntime().exec("getprop $name")
        input = BufferedReader(InputStreamReader(p.inputStream), 1024)
        line = input.readLine()
        input.close()
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    } finally {
        if (input != null) {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return line
}