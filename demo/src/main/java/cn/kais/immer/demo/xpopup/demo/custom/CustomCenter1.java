package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.core.CenterPopupView;

public class CustomCenter1 extends CenterPopupView {

    public CustomCenter1(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.temp2;
    }

    @Override
    protected int getMaxWidth() {
        return 0;
    }

}
