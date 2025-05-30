package cn.kais.immer.xpopup.impl;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.kais.immer.R;
import cn.kais.immer.xpopup.core.BottomPopupView;
import cn.kais.immer.xpopup.interfaces.OnSelectListener;
import cn.kais.immer.xpopup.util.XPopupUtils;
import cn.kais.immer.xpopup.widget.VerticalRecyclerView;

/**
 * Description: 底部的列表对话框
 * Create by dance, at 2018/12/16
 */
public class BottomListPopupView extends BottomPopupView {
    RecyclerView recyclerView;
    TextView tv_title, tv_cancel;
    View vv_divider;
    protected int bindLayoutId;
    protected int bindItemLayoutId;

    /**
     * @param context
     * @param bindLayoutId     layoutId 要求layoutId中必须有一个id为recyclerView的RecyclerView，如果你需要显示标题，则必须有一个id为tv_title的TextView
     * @param bindItemLayoutId itemLayoutId 条目的布局id，要求布局中有id为iv_image的ImageView（非必须），和id为tv_text的TextView
     */
    public BottomListPopupView(@NonNull Context context, int bindLayoutId, int bindItemLayoutId) {
        super(context);
        this.bindLayoutId = bindLayoutId;
        this.bindItemLayoutId = bindItemLayoutId;
        addInnerContent();
    }

    @Override
    protected int getImplLayoutId() {
        return bindLayoutId == 0 ? R.layout._xpopup_bottom_impl_list : bindLayoutId;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        LinearLayout box = findViewById(R.id.box);
        recyclerView = findViewById(R.id.recyclerView);
        if (bindLayoutId != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        tv_title = findViewById(R.id.tv_title);
        tv_cancel = findViewById(R.id.tv_cancel);
        vv_divider = findViewById(R.id.vv_divider);
        if (tv_cancel != null) {
            tv_cancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        if (tv_title != null) {
            if (TextUtils.isEmpty(title)) {
                tv_title.setVisibility(GONE);
                if (findViewById(R.id.xpopup_divider) != null)
                    findViewById(R.id.xpopup_divider).setVisibility(GONE);
            } else {
                tv_title.setText(title);
            }
        }

        BottomAdapter mAdapter = new BottomAdapter(getContext(), data, iconIds, checkedPosition);
        mAdapter.setOnClickListener((position, text) -> {
            if (selectListener != null) {
                selectListener.onSelect(position, text);
            }
            if (checkedPosition != -1) {
                checkedPosition = position;
                mAdapter.notifyDataSetChanged();
            }
            if (popupInfo.autoDismiss) dismiss();
        });
        recyclerView.setAdapter(mAdapter);
        applyTheme();
    }

    CharSequence title;
    String[] data;
    int[] iconIds;

    public BottomListPopupView setStringData(CharSequence title, String[] data, int[] iconIds) {
        this.title = title;
        this.data = data;
        this.iconIds = iconIds;
        return this;
    }

    private OnSelectListener selectListener;

    public BottomListPopupView setOnSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
        return this;
    }

    int checkedPosition = -1;

    /**
     * 设置默认选中的位置
     *
     * @param position
     * @return
     */
    public BottomListPopupView setCheckedPosition(int position) {
        this.checkedPosition = position;
        return this;
    }

    protected void applyTheme() {
        if (bindLayoutId == 0) {
            if (popupInfo.isDarkTheme) {
                applyDarkTheme();
            } else {
                applyLightTheme();
            }
        }
    }

    @Override
    protected void applyDarkTheme() {
        super.applyDarkTheme();
        ((VerticalRecyclerView) recyclerView).setupDivider(true);
        tv_title.setTextColor(getResources().getColor(R.color._xpopup_white_color));
        if (tv_cancel != null)
            tv_cancel.setTextColor(getResources().getColor(R.color._xpopup_white_color));
        findViewById(R.id.xpopup_divider).setBackgroundColor(
                getResources().getColor(R.color._xpopup_list_dark_divider)
        );
        if (vv_divider != null) vv_divider.setBackgroundColor(Color.parseColor("#1B1B1B"));
        getPopupImplView().setBackground(XPopupUtils.createDrawable(getResources().getColor(R.color._xpopup_dark_color),
                popupInfo.borderRadius, popupInfo.borderRadius, 0, 0));
    }

    @Override
    protected void applyLightTheme() {
        super.applyLightTheme();
        ((VerticalRecyclerView) recyclerView).setupDivider(false);
        tv_title.setTextColor(getResources().getColor(R.color._xpopup_dark_color));
        if (tv_cancel != null)
            tv_cancel.setTextColor(getResources().getColor(R.color._xpopup_dark_color));
        findViewById(R.id.xpopup_divider).setBackgroundColor(getResources().getColor(R.color._xpopup_list_divider));
        if (vv_divider != null)
            vv_divider.setBackgroundColor(getResources().getColor(R.color._xpopup_white_color));
        getPopupImplView().setBackground(XPopupUtils.createDrawable(getResources().getColor(R.color._xpopup_light_color),
                popupInfo.borderRadius, popupInfo.borderRadius, 0, 0));
    }

}
