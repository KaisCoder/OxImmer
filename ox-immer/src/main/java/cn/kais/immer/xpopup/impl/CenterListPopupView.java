package cn.kais.immer.xpopup.impl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.kais.immer.R;
import cn.kais.immer.xpopup.core.CenterPopupView;
import cn.kais.immer.xpopup.interfaces.OnSelectListener;
import cn.kais.immer.xpopup.widget.VerticalRecyclerView;

/**
 * Description: 在中间的列表对话框
 * Create by dance, at 2018/12/16
 */
public class CenterListPopupView extends CenterPopupView {
    RecyclerView recyclerView;
    TextView tv_title;

    /**
     * @param context
     * @param bindLayoutId     要求layoutId中必须有一个id为recyclerView的RecyclerView，如果你需要显示标题，则必须有一个id为tv_title的TextView
     * @param bindItemLayoutId 条目的布局id，要求布局中有id为iv_image的ImageView（非必须），和id为tv_text的TextView
     */
    public CenterListPopupView(@NonNull Context context, int bindLayoutId, int bindItemLayoutId) {
        super(context);
        this.bindLayoutId = bindLayoutId;
        this.bindItemLayoutId = bindItemLayoutId;
        addInnerContent();
    }

    @Override
    protected int getImplLayoutId() {
        return bindLayoutId == 0 ? R.layout._xpopup_center_impl_list : bindLayoutId;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        recyclerView = findViewById(R.id.recyclerView);
        if (bindLayoutId != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        tv_title = findViewById(R.id.tv_title);

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

    @Override
    protected void applyDarkTheme() {
        super.applyDarkTheme();
        ((VerticalRecyclerView) recyclerView).setupDivider(true);
        tv_title.setTextColor(getResources().getColor(R.color._xpopup_white_color));
        findViewById(R.id.xpopup_divider).setBackgroundColor(getResources().getColor(R.color._xpopup_list_dark_divider));
    }

    @Override
    protected void applyLightTheme() {
        super.applyLightTheme();
        ((VerticalRecyclerView) recyclerView).setupDivider(false);
        tv_title.setTextColor(getResources().getColor(R.color._xpopup_dark_color));
        findViewById(R.id.xpopup_divider).setBackgroundColor(getResources().getColor(R.color._xpopup_list_divider));
    }

    CharSequence title;
    String[] data;
    int[] iconIds;

    public CenterListPopupView setStringData(CharSequence title, String[] data, int[] iconIds) {
        this.title = title;
        this.data = data;
        this.iconIds = iconIds;
        return this;
    }

    private OnSelectListener selectListener;

    public CenterListPopupView setOnSelectListener(OnSelectListener selectListener) {
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
    public CenterListPopupView setCheckedPosition(int position) {
        this.checkedPosition = position;
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        return this;
    }

    protected int getMaxWidth() {
        if (popupInfo == null) return 0;
        return popupInfo.maxWidth == 0 ? super.getMaxWidth() : popupInfo.maxWidth;
    }
}
