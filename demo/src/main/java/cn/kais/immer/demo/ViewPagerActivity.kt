package cn.kais.immer.demo;

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import cn.kais.immer.demo.base.BaseAct
import cn.kais.immer.demo.databinding.ActivityViewPagerBinding
import cn.kais.immer.navigationBar

class ViewPagerActivity : BaseAct() {

    private lateinit var bind: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(bind.root)
//        setContentView(R.layout.activity_view_pager)
        bind.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        initViewPager()
        setTabSelect(0)
        bind.flAndroid.setOnClickListener { bind.viewPager.currentItem = 0 }
        bind.flAlbum.setOnClickListener { bind.viewPager.currentItem = 1 }
        bind.flCamera.setOnClickListener { bind.viewPager.currentItem = 2 }
        bind.flGames.setOnClickListener { bind.viewPager.currentItem = 3 }
        navigationBar {
            fitWindow = true
            colorRes = R.color.deepSkyBlue
            light = false
        }
    }

    private fun initViewPager() {
        bind.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setTabSelect(position)
            }
        })
    }

    private fun setTabSelect(index: Int) {
        bind.ivAndroid.setImageResource(R.drawable.ic_android_sliver_24dp)
        bind.ivAlbum.setImageResource(R.drawable.ic_album_sliver_24dp)
        bind.ivCamera.setImageResource(R.drawable.ic_camera_sliver_24dp)
        bind.ivGames.setImageResource(R.drawable.ic_games_sliver_24dp)
        when (index) {
            0 -> bind.ivAndroid.setImageResource(R.drawable.ic_android_deep_sky_blue_24dp)
            1 -> bind.ivAlbum.setImageResource(R.drawable.ic_album_deep_sky_blue_24dp)
            2 -> bind.ivCamera.setImageResource(R.drawable.ic_camera_deep_sky_blue_24dp)
            3 -> bind.ivGames.setImageResource(R.drawable.ic_games_deep_sky_blue_24dp)
        }
    }

}
