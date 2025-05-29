package cn.kais.immer.demo.xpopup.demo.fragment;

import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter4.BaseQuickAdapter;

import java.util.ArrayList;

import cn.kais.immer.demo.R;
import cn.kais.immer.demo.xpopup.demo.adapter.PartShadowAdapter;
import cn.kais.immer.demo.xpopup.demo.custom.CustomDrawerPopupView;
import cn.kais.immer.demo.xpopup.demo.custom.CustomPartShadowPopupView;
import cn.kais.immer.demo.xpopup.demo.custom.CustomPartShadowPopupView2;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.BasePopupView;
import cn.kais.immer.xpopup.enums.PopupPosition;
import cn.kais.immer.xpopup.interfaces.SimpleCallback;
import cn.kais.immer.xpopup.widget.VerticalRecyclerView;

/**
 * Description: 局部阴影的示例
 * Create by dance, at 2018/12/21
 */
public class PartShadowDemo extends BaseFragment implements View.OnClickListener {

    View ll_container;
    VerticalRecyclerView recyclerView;

    PartShadowAdapter mAdapter;

    private CustomPartShadowPopupView popupView;

    private CustomDrawerPopupView drawerPopupView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_part_shadow_demo;
    }

    @Override
    public void init(View view) {
        ll_container = view.findViewById(R.id.ll_container);
        recyclerView = view.findViewById(R.id.recyclerView);

        view.findViewById(R.id.tv_all).setOnClickListener(this);
        view.findViewById(R.id.tv_price).setOnClickListener(this);
        view.findViewById(R.id.tv_sales).setOnClickListener(this);
        view.findViewById(R.id.tv_select).setOnClickListener(this);
        view.findViewById(R.id.tv_filter).setOnClickListener(this);
        view.findViewById(R.id.tvCenter).setOnClickListener(this);
        view.findViewById(R.id.tvCenter2).setOnClickListener(this);

        drawerPopupView = new CustomDrawerPopupView(getContext());

        final ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i + "");
        }
        mAdapter = new PartShadowAdapter(data);
        // todo 这里这么写报错，不知道为什么
//        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener<String>() {
//            @Override
//            public boolean onLongClick(@NonNull BaseQuickAdapter<String, ?> baseQuickAdapter, @NonNull View view, int i) {
//                String item = baseQuickAdapter.getItem(i);
//                System.out.println(item);
//                final XPopup.Builder builder = new XPopup.Builder(getContext()).hasShadowBg(false);
//                builder.watchView(view).asAttachList(new String[]{"置顶", "编辑", "删除"}, null, new OnSelectListener() {
//                    @Override
//                    public void onSelect(int position, String text) {
//                        toast(text);
//                    }
//                }).show();
//                return true;
//            }
//        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<String>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<String, ?> baseQuickAdapter, @NonNull View view, int i) {
                toast(data.get(i));
            }
        });

//        EasyAdapter<String> adapter = new EasyAdapter<String>(data, android.R.layout.simple_list_item_1) {
//            @Override
//            protected void bind(@NonNull ViewHolder holder, @NonNull String s, int position) {
//                holder.getConvertView().setBackgroundColor(Color.parseColor("#fafafa"));
//                holder.setText(android.R.id.text1, "长按我试试 - " + position);
//                //必须要在事件发生之前就watch
//                final XPopup.Builder builder = new XPopup.Builder(getContext()).hasShadowBg(false).watchView(holder.getConvertView());
//                holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        builder.asAttachList(new String[]{"置顶", "编辑", "删除"}, null, new OnSelectListener() {
//                            @Override
//                            public void onSelect(int position, String text) {
//                                toast(text);
//                            }
//                        }).show();
//                        return true;
//                    }
//                });
//            }
//        };
//        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public boolean onItemLongClick(@NonNull View view, @NonNull RecyclerView.ViewHolder viewHolder, int i) {
//                return false;
//            }
//
//            @Override
//            public void onItemClick(@NonNull View view, @NonNull RecyclerView.ViewHolder holder, int position) {
//                toast(data.get(position));
//            }
//        });

        recyclerView.setupDivider(false);
//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(mAdapter);
    }

    private void showPartShadow(final View v) {
        if (popupView == null) {
            popupView = (CustomPartShadowPopupView) new XPopup.Builder(getContext())
                    .atView(v)
//                    .isClickThrough(true)
//                    .isViewMode(true)
//                    .isRequestFocus(false)
//                    .isTouchThrough(true)
//                    .notDismissWhenTouchInView(view.findViewById(R.id.tv_select))
//                    .isCenterHorizontal(true)
                    .autoOpenSoftInput(true)
//                    .offsetY(250)
//                    .offsetX(100)
                    .setPopupCallback(new SimpleCallback() {
                        @Override
                        public void onShow(BasePopupView popupView) {
                            toast("显示了");
                        }

                        @Override
                        public void onDismiss(BasePopupView popupView) {
                        }
                    })
                    .asCustom(new CustomPartShadowPopupView(getContext()));
        }

        popupView.show();
    }

    CustomPartShadowPopupView2 popupView2;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_all || id == R.id.tv_price || id == R.id.tv_sales) {
            showPartShadow(v);
        } else if (id == R.id.tv_filter) {
            new XPopup.Builder(getContext())
                    .isDestroyOnDismiss(true)
                    .popupPosition(PopupPosition.Right)//右边
//                        .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(drawerPopupView)
                    .show();
        } else if (id == R.id.tv_select) {
            new XPopup.Builder(getContext())
                    .atView(v)
                    .autoOpenSoftInput(true)
                    .moveUpToKeyboard(false)
                    .asCustom(new CustomPartShadowPopupView(getContext()))
                    .show();
        } else if (id == R.id.tvCenter) {
            new XPopup.Builder(getContext())
                    .atView(v)
                    .isViewMode(true)
                    .popupPosition(PopupPosition.Top)
                    .asCustom(new CustomPartShadowPopupView2(getContext(), Gravity.START))
                    .show();
        } else if (id == R.id.tvCenter2) {
            if (popupView2 == null) {
                popupView2 = new CustomPartShadowPopupView2(getContext(), Gravity.END);
            }
            new XPopup.Builder(getContext())
                    .atView(v)
                    .isViewMode(true)
                    .popupPosition(PopupPosition.Bottom)
                    .asCustom(popupView2)
                    .show();
        }
    }

}
