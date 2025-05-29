package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.core.BubbleHorizontalAttachPopupView;
import cn.kais.immer.xpopup.util.XPopupUtils;

/**
 * Description: 自定义Attach弹窗，水平方向的带气泡的弹窗
 * Create by lxj, at 2019/3/13
 */
public class CustomHorizontalBubbleAttachPopup extends BubbleHorizontalAttachPopupView {

    public CustomHorizontalBubbleAttachPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        setBubbleBgColor(Color.parseColor("#4D5063"));
        setBubbleShadowSize(XPopupUtils.dp2px(getContext(), 3));
        setBubbleShadowColor(Color.BLACK);
        getPopupImplView().setBackgroundResource(0);
        findViewById(R.id.tv_zan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "赞", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
        findViewById(R.id.tv_comment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "评论", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
    }

}
