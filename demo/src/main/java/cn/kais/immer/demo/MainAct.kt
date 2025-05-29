package cn.kais.immer.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import cn.kais.immer.demo.adapter.MainAdapter
import cn.kais.immer.demo.adjustresize.AdjustResizeActivity
import cn.kais.immer.demo.base.BaseAct
import cn.kais.immer.demo.base.BaseCallback
import cn.kais.immer.demo.bean.BtnItem
import cn.kais.immer.demo.bottomnav.BottomNavActivity
import cn.kais.immer.demo.case.CaseImmersionAct
import cn.kais.immer.demo.databinding.ActMainBinding
import cn.kais.immer.demo.xpopup.demo.MainActivity2
import cn.kais.immer.navigationBar
import cn.kais.immer.statusBar
import com.chad.library.adapter4.QuickAdapterHelper
import com.hjq.permissions.Permission

class MainAct : BaseAct() {

    private lateinit var bind: ActMainBinding

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        MainAdapter(items)
    }

    private val helper by lazy(LazyThreadSafetyMode.NONE) {
        QuickAdapterHelper.Builder(adapter).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        setSupportActionBar(bind.toolbar)

        statusBar {
            fitWindow = true
            colorRes = R.color.deepSkyBlue
        }

        navigationBar {
            fitWindow = true
            colorRes = R.color.deepSkyBlue
        }

        bind.listview.adapter = helper.adapter
        adapter.addOnItemChildClickListener(R.id.btn) { _, _, position ->
            val item: BtnItem = adapter.getItem(position) ?: return@addOnItemChildClickListener
            if (item.code === "1") {
                doCheckPermission(Permission.READ_CONTACTS, Permission.WRITE_CONTACTS, Permission.GET_ACCOUNTS, callback = object : BaseCallback() {
                    override fun onPermissionSuccess() {
                        doStart(item.clazz)
                    }
                })
            } else {
                doStart(item.clazz)
            }
        }

    }

    /**
     * 跳转
     */
    private fun doStart(clazz: Class<out Activity>) {
        startActivity(Intent(this, clazz))
    }

//    private fun doCreateBtnItems(): ArrayList<BtnItem> {
//        return arrayListOf(
//            BtnItem("to case immersion", "1", CaseImmersionAct::class.java),
//            BtnItem("transparent", "0", TransparentActivity::class.java),
//            BtnItem("switch (Activity)", "0", SwitchActivity::class.java),
//            BtnItem("switch (Fragment)", "0", SwitchFragmentActivity::class.java),
//            BtnItem("viewpager (apply to Fragment)", "0", ViewPagerActivity::class.java),
//            BtnItem("viewpager (apply to Activity)", "0", ViewPagerActivity2::class.java),
//            BtnItem("scroll", "0", ScrollActivity::class.java),
//            BtnItem("drawerlayout", "0", DrawerActivity::class.java),
//            BtnItem("Fragment RecyclerView", "0", RecyclerFragmentActivity::class.java),
//            BtnItem("Fragment Stack", "0", FragmentStackActivity::class.java),
//            BtnItem("View Add Padding", "0", AddPaddingActivity::class.java),
//            BtnItem("CoordinatorLayout", "0", CoordinatorActivity::class.java),
//            BtnItem("BottomNavigationView", "0", BottomNavActivity::class.java),
//            BtnItem("AdjustResize", "0", AdjustResizeActivity::class.java),
//            BtnItem("toXPopup", "0", MainActivity2::class.java)
//        )
//    }

    private val items: ArrayList<BtnItem>
        get() = arrayListOf(
            BtnItem("to case immersion", "1", CaseImmersionAct::class.java),
            BtnItem("transparent", "0", TransparentActivity::class.java),
            BtnItem("switch (Activity)", "0", SwitchActivity::class.java),
            BtnItem("switch (Fragment)", "0", SwitchFragmentActivity::class.java),
            BtnItem("viewpager (apply to Fragment)", "0", ViewPagerActivity::class.java),
            BtnItem("viewpager (apply to Activity)", "0", ViewPagerActivity2::class.java),
            BtnItem("scroll", "0", ScrollActivity::class.java),
            BtnItem("drawerlayout", "0", DrawerActivity::class.java),
            BtnItem("Fragment RecyclerView", "0", RecyclerFragmentActivity::class.java),
            BtnItem("Fragment Stack", "0", FragmentStackActivity::class.java),
            BtnItem("View Add Padding", "0", AddPaddingActivity::class.java),
            BtnItem("CoordinatorLayout", "0", CoordinatorActivity::class.java),
            BtnItem("BottomNavigationView", "0", BottomNavActivity::class.java),
            BtnItem("AdjustResize", "0", AdjustResizeActivity::class.java),
            BtnItem("toXPopup", "0", MainActivity2::class.java)
        )

}
