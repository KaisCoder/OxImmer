package cn.kais.immer.demo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import cn.kais.immer.demo.base.BaseAct;
import cn.kais.immer.java.UltimateBarX;

/**
 * @Author : Zackratos
 * @Date : 2020/7/11 3:58
 * @email : 869649338@qq.com
 * @Describe :
 */
public class DrawerActivity extends BaseAct {

    TextFragment2 mainFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        UltimateBarX.statusBarOnly(this)
                .fitWindow(true)
                .color(Color.RED)
                .apply();
        initMainFragment();
        addFragment();
    }

    private void initMainFragment() {
        mainFragment = TextFragment2.Companion.newInstance(Color.RED, "九阴真经", Color.WHITE,
                fragment -> {
                    UltimateBarX.statusBarOnly(fragment)
                            .color(Color.RED)
                            .fitWindow(true)
                            .apply();
                    initToolbar(fragment);
                    return null;
                });
    }

    private void initToolbar(Fragment fragment) {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = fragment.requireView().findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flMain, mainFragment)
                .add(R.id.flDrawer, DrawerFragment.newInstance())
                .commit();
    }

}
