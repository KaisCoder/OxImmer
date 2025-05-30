package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.core.BubbleAttachPopupView;
import cn.kais.immer.xpopup.util.XPopupUtils;

/**
 * Description: 自定义气泡Attach弹窗
 * Create by lxj, at 2019/3/13
 */
public class CustomBubbleAttachPopup extends BubbleAttachPopupView {

    public CustomBubbleAttachPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bubble_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setBubbleBgColor(Color.BLUE);
        setBubbleShadowSize(XPopupUtils.dp2px(getContext(), 6));
        setBubbleShadowColor(Color.RED);
        setArrowWidth(XPopupUtils.dp2px(getContext(), 8));
        setArrowHeight(XPopupUtils.dp2px(getContext(), 9));
//                                .setBubbleRadius(100)
        setArrowRadius(XPopupUtils.dp2px(getContext(), 2));
        final TextView tv = findViewById(R.id.tv);
        Glide.with(getContext()).load("https://t7.baidu.com/it/u=963301259,1982396977&fm=193&f=GIF").into((ImageView) findViewById(R.id.image));
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                tv.setText(tv.getText() + "\n 啊哈哈哈啊哈");
//                tv.setText("\n 啊哈哈哈啊哈");
                dismiss();
            }
        });
    }

}
