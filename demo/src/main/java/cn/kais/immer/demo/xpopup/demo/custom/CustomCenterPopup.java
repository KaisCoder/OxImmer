package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.core.CenterPopupView;

public class CustomCenterPopup extends CenterPopupView {

    public CustomCenterPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_custom_center;
    }

}
