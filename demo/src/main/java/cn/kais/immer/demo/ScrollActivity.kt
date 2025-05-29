package cn.kais.immer.demo;

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import cn.kais.immer.addStatusBarTopPadding
import cn.kais.immer.demo.base.BaseAct
import cn.kais.immer.demo.databinding.FragmentScrollBinding
import cn.kais.immer.getStatusBarOnly
import cn.kais.immer.statusBarOnly

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  10:34 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class ScrollActivity : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = FragmentScrollBinding.inflate(layoutInflater)
        setContentView(bind.root)
//        setContentView(R.layout.fragment_scroll)
        bind.toolbar.title = "九阴真经"
        statusBarOnly { transparent() }
        bind.toolbar.addStatusBarTopPadding()
        bind.scrollView.setOnScrollChangeListener { _: NestedScrollView?, _, scrollY: Int, _, oldScrollY: Int ->
            val height = bind.imageView.height - bind.toolbar.height
            if (height in (oldScrollY + 1)..scrollY) {
                getStatusBarOnly {
                    light = true
                    lvlColor = Color.GRAY
                }
                bind.toolbar.visibility = View.VISIBLE
            } else if (height in (scrollY + 1)..oldScrollY) {
                getStatusBarOnly {
                    light = false
                }
                bind.toolbar.visibility = View.INVISIBLE
            }
        }
    }

}