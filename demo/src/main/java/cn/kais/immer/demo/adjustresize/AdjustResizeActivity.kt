package cn.kais.immer.demo.adjustresize

import android.os.Bundle
import cn.kais.immer.demo.ViewBindingActivity
import cn.kais.immer.demo.databinding.ActivityAdjustResizeBinding
import cn.kais.immer.statusBarOnly

/**
 * @Author   : zackratos
 * @Date     : 2021/8/20 5:26 下午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class AdjustResizeActivity : ViewBindingActivity<ActivityAdjustResizeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarOnly { transparent() }
    }

}