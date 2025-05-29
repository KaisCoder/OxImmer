package cn.kais.immer.java;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import cn.kais.immer.OxImmerKt;
import cn.kais.immer.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/8/26 8:06 下午
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class StatusBarOperator extends BaseOperator {

    public StatusBarOperator(LifecycleOwner owner) {
        this(owner, BarConfig.Companion.newInstance());
    }

    public StatusBarOperator(LifecycleOwner owner, BarConfig config) {
        super(owner, config);
    }

    @Override
    protected void applyActivity(FragmentActivity activity) {
        OxImmerKt.statusBar(activity, config, null);
    }

    @Override
    protected void applyFragment(Fragment fragment) {
        OxImmerKt.statusBar(fragment, config, null);
    }

}
