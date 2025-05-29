package cn.kais.immer.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import cn.kais.immer.demo.base.BaseAct;
import cn.kais.immer.java.UltimateBarX;

/**
 * @Author : zhangwenchao
 * @Date : 2021/1/5  9:10 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class AddPaddingActivity extends BaseAct {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_padding);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("九阴真经");
        TextView tvBottom = findViewById(R.id.tvBottom);
        UltimateBarX.statusBar(this)
                .transparent()
                .apply();

        UltimateBarX.navigationBar(this)
                .transparent()
                .apply();

        UltimateBarX.addStatusBarTopPadding(toolbar);
        UltimateBarX.addNavigationBarBottomPadding(tvBottom);
    }

}
