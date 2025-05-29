package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.impl.PartShadowPopupView;

/**
 * Description: 自定义局部阴影弹窗
 * Create by dance, at 2018/12/21
 */
public class CustomPartShadowPopupView2 extends PartShadowPopupView {

    int gravity;

    public CustomPartShadowPopupView2(@NonNull Context context, int gravity) {
        super(context);
        this.gravity = gravity;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_part_shadow_popup2;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        LayoutParams params = (LayoutParams) findViewById(R.id.ll).getLayoutParams();
        params.gravity = gravity;
    }

}
