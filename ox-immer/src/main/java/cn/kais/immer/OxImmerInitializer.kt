package cn.kais.immer

import android.content.Context
import androidx.startup.Initializer

/**
 * 启动自动初始化
 */
class OxImmerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        OxImmerController.instance.context = context
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
        return mutableListOf()
    }

}