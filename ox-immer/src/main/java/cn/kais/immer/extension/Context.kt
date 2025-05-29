package cn.kais.immer.extension

import android.content.Context
import android.content.res.Configuration
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/28  7:09 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */

private fun Context.getBarHeight(name: String): Int = resources.getDimensionPixelSize(resources.getIdentifier(name, "dimen", "android"))

internal val Context.statusBarHeight: Int
    get() = getBarHeight("status_bar_height")

internal val Context.navigationBarHeight: Int
    get() = getBarHeight("navigation_bar_height")

internal val Context.landscape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

@ColorInt
internal fun Context.getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

internal val Context.screenHeight: Int
    get() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getRealSize(point)
        return point.y
    }

internal fun Context.commonNavigationBarExist(): Boolean {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val d = wm.defaultDisplay
    val realDisplayMetrics = DisplayMetrics()

    d.getRealMetrics(realDisplayMetrics)

    val realHeight = realDisplayMetrics.heightPixels
    val realWidth = realDisplayMetrics.widthPixels

    val displayMetrics = DisplayMetrics()
    d.getMetrics(displayMetrics)

    val displayHeight = displayMetrics.heightPixels
    val displayWidth = displayMetrics.widthPixels

    return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
}