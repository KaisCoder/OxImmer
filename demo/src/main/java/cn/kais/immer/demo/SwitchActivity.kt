package cn.kais.immer.demo;

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import cn.kais.immer.bean.BarBackground
import cn.kais.immer.bean.BarConfig
import cn.kais.immer.demo.base.BaseAct
import cn.kais.immer.demo.databinding.ActivitySwitchBinding
import cn.kais.immer.demo.extension.getResColor
import cn.kais.immer.navigationBar
import cn.kais.immer.statusBar

class SwitchActivity : BaseAct() {

    private lateinit var bind: ActivitySwitchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySwitchBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_switch)
        setContentView(bind.root)
        setRootClick()
        setApply()
        bind.tvApply.performClick()
    }

    private var time = 0L
    private var lastTime = 0L

    private fun setRootClick() {
        bind.rlRoot.setOnClickListener {
            lastTime = time
            time = System.currentTimeMillis()
            if (time - lastTime < 300) {
                controlVisibility(View.VISIBLE)
                bind.tvTips.visibility = View.GONE
                lastTime = 0L
                time = 0L
            }
        }
    }

    private fun setApply() {
        bind.tvApply.setOnClickListener {
            when (bind.rgType.checkedRadioButtonId) {
                R.id.rbStatus -> statusBar(::setConfig)
                R.id.rbNavigation -> navigationBar(::setConfig)
            }
        }
    }

    private fun controlVisibility(visibility: Int) {
        bind.rgType.visibility = visibility
        bind.rgFitWindow.visibility = visibility
        bind.rgLight.visibility = visibility
        bind.rgColor.visibility = visibility
        bind.tvApply.visibility = visibility
    }

    private fun setConfig(config: BarConfig) {
        config.run {
            fitWindow = getFitWindow()
            light = getLight()
            background = getBackground()
        }
    }

    private fun getBackground(): BarBackground =
        BarBackground.newInstance().apply {
            when (bind.rgColor.checkedRadioButtonId) {
                R.id.rbGradient -> drawableRes = R.drawable.bg_gradient
                else -> color = this@SwitchActivity.getColor()
            }
        }

    private fun getLight(): Boolean = when (bind.rgLight.checkedRadioButtonId) {
        R.id.rbLight -> true
        R.id.rbNoLight -> false
        else -> false
    }

    private fun getFitWindow(): Boolean = when (bind.rgFitWindow.checkedRadioButtonId) {
        R.id.rbFitWindow -> true
        R.id.rbNoFitWindow -> false
        else -> false
    }

    @ColorInt
    private fun getColor(): Int = when (bind.rgColor.checkedRadioButtonId) {
        R.id.rbRed -> Color.RED
        R.id.rbCyan -> getResColor(R.color.cyan, this)
        R.id.rbTransparent -> Color.TRANSPARENT
        R.id.rbAlphaBlack -> getResColor(R.color.alphaBlack, this)
        R.id.rbAlphaGreen -> getResColor(R.color.alphaGreen, this)
        else -> Color.TRANSPARENT
    }

    override fun onBackPressed() {
        if (bind.tvApply.visibility == View.VISIBLE) {
            controlVisibility(View.INVISIBLE)
            return
        }
        super.onBackPressed()
    }

}
