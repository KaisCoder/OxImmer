package cn.kais.immer.demo;

import android.graphics.Color;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import cn.kais.immer.java.UltimateBarX;

/**
 * @Author : zhangwenchao
 * @Date : 2020/7/8  2:57 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> fragments;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments = new SparseArray<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = TextFragment2
                        .Companion
                        .newInstance(Color.RED, "Android", Color.WHITE, f -> {
                            UltimateBarX.statusBar(f)
                                    .fitWindow(true)
                                    .color(Color.RED)
                                    .apply();
                            return null;
                        });
                break;
            case 1:
                fragment = ImageTextFragment
                        .Companion
                        .newInstance(R.drawable.yurisa__001, f -> {
                            UltimateBarX.statusBar(f)
                                    .fitWindow(false)
                                    .colorRes(R.color.alphaWhite)
                                    .light(true)
                                    .lvlColorRes(R.color.alphaBlack)
                                    .apply();
                            return null;
                        });
                break;
            case 2:
                fragment = ImageTextFragment
                        .Companion
                        .newInstance(R.drawable.yurisa__006, f -> {
                            UltimateBarX.statusBar(f)
                                    .transparent()
                                    .apply();
                            return null;
                        });
                break;
            case 3:
                fragment = TextFragment2
                        .Companion
                        .newInstance(Color.YELLOW, "Camera", Color.BLACK, f -> {
                            UltimateBarX.statusBar(f)
                                    .fitWindow(true)
                                    .color(Color.YELLOW)
                                    .light(true)
                                    .lvlDrawableRes(R.drawable.bg_yellow_alpha_black)
                                    .apply();
                            return null;
                        });
                break;
            default:
                fragment = new Fragment();
                break;
        }
        fragments.put(position, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
