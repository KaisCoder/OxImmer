package cn.kais.immer.demo;

import android.os.Bundle
import cn.kais.immer.demo.base.BaseAct
import cn.kais.immer.navigationBar
import cn.kais.immer.statusBar

class TransparentActivity : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)
        statusBar { transparent() }
        navigationBar { transparent() }
    }

}
