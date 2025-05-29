package cn.kais.immer.demo.case

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import cn.kais.immer.addStatusBarTopPadding
import cn.kais.immer.demo.R
import cn.kais.immer.demo.base.BaseAct
import cn.kais.immer.demo.xpopup.demo.custom.CustomBubbleAttachPopup
import cn.kais.immer.demo.xpopup.demo.custom.PagerDrawerPopup
import cn.kais.immer.navigationBar
import cn.kais.immer.statusBar
import cn.kais.immer.xpopup.XPopup
import cn.kais.immer.xpopup.enums.PopupPosition
import cn.kais.immer.xpopup.impl.LoadingPopupView
import cn.kais.immer.xpopup.interfaces.OnCancelListener
import cn.kais.immer.xpopup.interfaces.OnConfirmListener

class CaseImmersionAct : BaseAct() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_case_immersion)

        statusBar {
            transparent()
        }

        navigationBar {
            transparent()
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setBackgroundColor(Color.TRANSPARENT)
        toolbar.title = "最理想的案例~"
        toolbar.subtitle = "实现沉浸式效果最完美案例~~~"
        toolbar.addStatusBarTopPadding()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        onShowLoad()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.case_status_bar_menus, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                onBackPressed()
                doShowExit()
            }

            R.id.menu_btn_1 -> {
                val view = findViewById<View>(R.id.menu_btn_1)
                onShowBubble(view)
            }

            R.id.menu_btn_2 -> {
                onShowDrawer()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    var load: LoadingPopupView? = null
    private fun onShowLoad() {
        if (load == null) {
            // 使用了Fragment，必须开启View模式  这个东西可以完美实现一体化 沉浸式弹窗
            val builder = XPopup.Builder(this).dismissOnBackPressed(false).isViewMode(true)
            load = builder.asLoading("少时诵诗书", LoadingPopupView.Style.ProgressBar).show() as LoadingPopupView
        } else {
            load?.setStyle(LoadingPopupView.Style.ProgressBar)
            load?.show()
        }
        load?.postDelayed(object : Runnable {
            override fun run() {
                load?.setTitle("加载中长度变化啊")
                load?.setStyle(LoadingPopupView.Style.Spinner)
                load?.postDelayed(object : Runnable {
                    override fun run() {
                        load?.setTitle("")
                    }
                }, 2000)
            }
        }, 2000)
        load?.delayDismissWith(6000, object : Runnable {
            override fun run() {
                Toast.makeText(baseContext, "我消失了！！！", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onShowBubble(view: View) {
        val builder = XPopup.Builder(this)
        builder.isTouchThrough(true)
        builder.isDestroyOnDismiss(true)
        builder.atView(view)
        builder.isViewMode(true)
        builder.hasShadowBg(false)
        builder.asCustom(CustomBubbleAttachPopup(this)).show()
    }

    private fun onShowDrawer() {
        val builder = XPopup.Builder(this)
        builder.isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
        builder.popupPosition(PopupPosition.Right)//右边
        builder.isViewMode(true) //使用了Fragment，必须开启View模式
        builder.asCustom(PagerDrawerPopup(this)).show()
    }

    private fun doShowExit() {
        val builder = XPopup.Builder(this)
        builder.isDestroyOnDismiss(true)
        builder.isViewMode(true) //使用了Fragment，必须开启View模式
        builder.asConfirm("退出页面", "床前明月光，\n疑是地上霜。\n举头望明月，\n低头思故乡。", "取消", "确定", object : OnConfirmListener {
            override fun onConfirm() {
                Toast.makeText(baseContext, "确定退出页面", Toast.LENGTH_SHORT).show()
                finish()
            }
        }, object : OnCancelListener {
            override fun onCancel() {
                Toast.makeText(baseContext, "暂不退出", Toast.LENGTH_SHORT).show()
            }
        }, false).show()
    }

}