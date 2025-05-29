package cn.kais.immer.demo

import androidx.multidex.MultiDexApplication

class Demo : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
//        ToastUtils.getDefaultMaker().setGravity(Gravity.CENTER, 0, 0);
//        ToastUtils.getDefaultMaker().setBgResource(R.drawable.bg_toast);
//        ToastUtils.getDefaultMaker().setTextColor(Color.WHITE);
    }

}