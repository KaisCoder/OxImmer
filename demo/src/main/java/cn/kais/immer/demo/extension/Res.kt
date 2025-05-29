package cn.kais.immer.demo.extension

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @Author   : zackratos
 * @Date     : 2021/8/23 7:55 下午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
@ColorInt
fun getResColor(@ColorRes colorRes: Int, context: Context): Int = ContextCompat.getColor(context, colorRes)