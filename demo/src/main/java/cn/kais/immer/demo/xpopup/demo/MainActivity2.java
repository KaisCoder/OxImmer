package cn.kais.immer.demo.xpopup.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import cn.kais.immer.demo.R;
import cn.kais.immer.demo.base.BaseAct;
import cn.kais.immer.demo.xpopup.demo.fragment.AllAnimatorDemo;
import cn.kais.immer.demo.xpopup.demo.fragment.CustomAnimatorDemo;
import cn.kais.immer.demo.xpopup.demo.fragment.CustomPopupDemo;
import cn.kais.immer.demo.xpopup.demo.fragment.ImageViewerDemo;
import cn.kais.immer.demo.xpopup.demo.fragment.PartShadowDemo;
import cn.kais.immer.demo.xpopup.demo.fragment.QuickStartDemo;
import cn.kais.immer.java.UltimateBarX;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.BasePopupView;
import cn.kais.immer.xpopup.impl.LoadingPopupView;
import cn.kais.immer.xpopup.util.XPopupUtils;

public class MainActivity2 extends BaseAct {

    PageInfo[] pageInfos = new PageInfo[]{
            new PageInfo("快速开始", new QuickStartDemo()),
            new PageInfo("局部阴影", new PartShadowDemo()),
            new PageInfo("图片浏览", new ImageViewerDemo()),
            new PageInfo("尝试不同动画", new AllAnimatorDemo()),
            new PageInfo("自定义弹窗", new CustomPopupDemo()),
            new PageInfo("自定义动画", new CustomAnimatorDemo())
    };

    TabLayout tabLayout;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        UltimateBarX.navigationBar(this).transparent().apply();
        UltimateBarX.statusBar(this).transparent().apply();

//        BarUtils.setStatusBarLightMode(this, false);
//        BarUtils.setNavBarColor(this, Color.RED);
//        BarUtils.setStatusBarVisibility();
//        BarUtils.setNavBarColor(this, Color.TRANSPARENT);
//        BarUtils.setNavBarLightMode(this, true);
//        BarUtils.setNavBarVisibility(MainActivity.this, false);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle(actionBar.getTitle() + "-" + BuildConfig.VERSION_NAME);

        AppBarLayout bar = findViewById(R.id.appBarLayout);
        UltimateBarX.addStatusBarTopPadding(bar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        XPopup.setPrimaryColor(getResources().getColor(R.color.colorPrimary));
//        XPopup.setAnimationDuration(400);
//        XPopup.setIsLightStatusBar(false);
//        XPopup.setPrimaryColor(Color.RED);
//        XPopup.setIsLightStatusBar(true);
//        XPopup.setNavigationBarColor(Color.RED);
        final BasePopupView loadingPopupView = new XPopup.Builder(this)
                .isDestroyOnDismiss(true)
                .asLoading(null, LoadingPopupView.Style.ProgressBar).show();

        loadingPopupView.delayDismiss(1200);


//        new XPopup.Builder(this).asConfirm("asda", "dasdadas", null).show();

        String str = "deviceHeight：" + XPopupUtils.getScreenHeight(MainActivity2.this)
                + "  getAppHeight: " + XPopupUtils.getAppHeight(MainActivity2.this)
                + " deviceWidth: " + XPopupUtils.getScreenWidth(MainActivity2.this)
                + " getAppWidth: " + XPopupUtils.getAppWidth(MainActivity2.this)
                + "  statusHeight: " + XPopupUtils.getStatusBarHeight(getWindow())
                + "  navHeight: " + XPopupUtils.getNavBarHeight(getWindow());
//                + "  hasNav: " + XPopupUtils.isNavBarVisible(getWindow());
        Log.d("tag", str);
    }

    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pageInfos[i].fragment;
        }

        @Override
        public int getCount() {
            return pageInfos.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageInfos[position].title;
        }
    }

}
