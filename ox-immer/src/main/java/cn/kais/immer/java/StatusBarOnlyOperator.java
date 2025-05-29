package cn.kais.immer.java;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import cn.kais.immer.OxImmerKt;
import cn.kais.immer.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/11/10 16:48
 * @Describe :
 */
public class StatusBarOnlyOperator extends BaseOperator {

    public StatusBarOnlyOperator(LifecycleOwner owner) {
        this(owner, BarConfig.Companion.newInstance());
    }

    public StatusBarOnlyOperator(LifecycleOwner owner, BarConfig config) {
        super(owner, config);
    }

    @Override
    protected void applyActivity(FragmentActivity activity) {
        OxImmerKt.statusBarOnly(activity, config, null);
    }

    @Override
    protected void applyFragment(Fragment fragment) {
        OxImmerKt.statusBarOnly(fragment, config, null);
    }

}
