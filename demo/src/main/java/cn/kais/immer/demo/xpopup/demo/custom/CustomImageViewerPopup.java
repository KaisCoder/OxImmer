package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.ImageViewerPopupView;
import cn.kais.immer.xpopup.interfaces.OnSelectListener;

/**
 * Description: 自定义大图浏览弹窗
 * Create by dance, at 2019/5/8
 */
public class CustomImageViewerPopup extends ImageViewerPopupView {

    public CustomImageViewerPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_image_viewer_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
//        tv_pager_indicator.setVisibility(GONE);
        findViewById(R.id.tvClickMe).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new XPopup.Builder(getContext()).asBottomList("提示", new String[]{"保存照片"}, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        Toast.makeText(getContext(), "你自己实现保存照片", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "CustomImageViewerPopup onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomImageViewerPopup onDismiss");
    }

}
