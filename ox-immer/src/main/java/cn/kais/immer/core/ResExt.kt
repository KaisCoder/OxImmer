package cn.kais.immer.core

import android.content.res.Resources
import android.util.TypedValue

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/8/17  9:24 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
internal val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()