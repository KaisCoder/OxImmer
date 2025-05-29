package cn.kais.immer.java;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import cn.kais.immer.OxImmerKt;
import cn.kais.immer.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/8/26 8:01 下午
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class UltimateBarX {

    public static Operator statusBar(FragmentActivity activity) {
        return new StatusBarOperator(activity);
    }

    public static Operator navigationBar(FragmentActivity activity) {
        return new NavigationBarOperator(activity);
    }

    public static Operator getStatusBar(FragmentActivity activity) {
        return new StatusBarOperator(activity, getStatusBarConfig(activity));
    }

    public static Operator getNavigationBar(FragmentActivity activity) {
        return new NavigationBarOperator(activity, getNavigationBarConfig(activity));
    }

    public static Operator statusBarOnly(FragmentActivity activity) {
        return new StatusBarOnlyOperator(activity);
    }

    public static Operator getStatusBarOnly(FragmentActivity activity) {
        return new StatusBarOnlyOperator(activity, getStatusBarConfig(activity));
    }

    public static Operator statusBar(Fragment fragment) {
        return new StatusBarOperator(fragment);
    }

    public static Operator navigationBar(Fragment fragment) {
        return new NavigationBarOperator(fragment);
    }

    public static Operator getStatusBar(Fragment fragment) {
        return new StatusBarOperator(fragment, getStatusBarConfig(fragment));
    }

    public static Operator getNavigationBar(Fragment fragment) {
        return new NavigationBarOperator(fragment, getNavigationBarConfig(fragment));
    }

    public static Operator statusBarOnly(Fragment fragment) {
        return new StatusBarOnlyOperator(fragment);
    }

    public static Operator getStatusBarOnly(Fragment fragment) {
        return new StatusBarOnlyOperator(fragment, getStatusBarConfig(fragment));
    }

    public static void addStatusBarTopPadding(View view) {
        OxImmerKt.addStatusBarTopPadding(view);
    }

    public static void addNavigationBarBottomPadding(View view) {
        OxImmerKt.addNavigationBarBottomPadding(view);
    }

    public static BarConfig getStatusBarConfig(FragmentActivity activity) {
        return OxImmerKt.getStatusBarConfig(activity);
    }

    public static BarConfig getNavigationBarConfig(FragmentActivity activity) {
        return OxImmerKt.getNavigationBarConfig(activity);
    }

    public static BarConfig getStatusBarConfig(Fragment fragment) {
        return OxImmerKt.getStatusBarConfig(fragment);
    }

    public static BarConfig getNavigationBarConfig(Fragment fragment) {
        return OxImmerKt.getNavigationBarConfig(fragment);
    }

    public static int getStatusBarHeight() {
        return OxImmerKt.getStatusBarHeight();
    }

    public static int getNavigationBarHeight() {
        return OxImmerKt.getNavigationBarHeight();
    }

}
