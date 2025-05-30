package cn.kais.immer

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import cn.kais.immer.bean.BarConfig
import cn.kais.immer.extension.getRom
import cn.kais.immer.extension.originNavigationBarColor
import cn.kais.immer.extension.originStatusBarColor
import cn.kais.immer.rom.Rom
import java.lang.reflect.Field

internal class OxImmerController private constructor() {

    companion object {
        val instance: OxImmerController
            get() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = OxImmerController()
    }

    val rom: Rom by lazy { getRom() }

    internal lateinit var context: Context

    internal val fragmentViewFiled: Field by lazy { Fragment::class.java.getDeclaredField("mView").apply { isAccessible = true } }

    // 保存 Activity 的 StatusBar 是否设置过
    private val staDefMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }

    // 保存 Activity 的 NavigationBar 是否设置过
    private val navDefMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }

    // 保存是否已经 AddObserver
    private val addObsMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }

    // 保存是否已经初始化
    private val initializationMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }

    private val staConfigMap: MutableMap<String, BarConfig> by lazy { ArrayMap<String, BarConfig>() }

    private val navConfigMap: MutableMap<String, BarConfig> by lazy { ArrayMap<String, BarConfig>() }

    internal fun removeAllData(owner: LifecycleOwner) {
        val key = owner.hashCode().toString()
        staDefMap.remove(key)
        navDefMap.remove(key)
        addObsMap.remove(key)
        initializationMap.remove(key)
        staConfigMap.remove(key)
        navConfigMap.remove(key)
    }

    internal fun putAddObserver(owner: LifecycleOwner) {
        addObsMap[owner.hashCode().toString()] = true
    }

    internal fun getAddObserver(owner: LifecycleOwner): Boolean = addObsMap[owner.hashCode().toString()] ?: false

    internal fun putStatusBarDefault(owner: LifecycleOwner) {
        staDefMap[owner.hashCode().toString()] = true
    }

    internal fun putNavigationBarDefault(owner: LifecycleOwner) {
        navDefMap[owner.hashCode().toString()] = true
    }

    internal fun getStatusBarDefault(owner: LifecycleOwner) = staDefMap[owner.hashCode().toString()] ?: false

    internal fun getNavigationBarDefault(owner: LifecycleOwner) = navDefMap[owner.hashCode().toString()] ?: false

    internal fun getInitialization(owner: LifecycleOwner): Boolean = initializationMap[owner.hashCode().toString()] ?: false

    internal fun putInitialization(owner: LifecycleOwner) {
        initializationMap[owner.hashCode().toString()] = true
    }

    internal fun putOriginConfig(activity: FragmentActivity) {
        putOriginStatusBarConfig(activity)
        putOriginNavigationBarConfig(activity)
    }

    internal fun putOriginStatusBarConfig(activity: FragmentActivity) {
        val staConfig = getStatusBarConfig(activity)
        staConfig.background.color = activity.originStatusBarColor
        putStatusBarConfig(activity, staConfig)
    }

    internal fun putOriginNavigationBarConfig(activity: FragmentActivity) {
        val navConfig = getNavigationBarConfig(activity)
        navConfig.background.color = activity.originNavigationBarColor
        navConfig.light = calculateLight(navConfig.background.color)
        putNavigationBarConfig(activity, navConfig)
    }

    private fun calculateLight(@ColorInt color: Int) = color > (Color.BLACK + Color.WHITE / 2)

    internal fun putStatusBarConfig(owner: LifecycleOwner, config: BarConfig) {
        staConfigMap[owner.hashCode().toString()] = config
    }

    internal fun getStatusBarConfig(owner: LifecycleOwner): BarConfig = staConfigMap[owner.hashCode().toString()] ?: BarConfig.newInstance()

    internal fun putNavigationBarConfig(owner: LifecycleOwner, config: BarConfig) {
        navConfigMap[owner.hashCode().toString()] = config
    }

    internal fun getNavigationBarConfig(owner: LifecycleOwner): BarConfig {
        return navConfigMap[owner.hashCode().toString()] ?: BarConfig.newInstance()
    }

}