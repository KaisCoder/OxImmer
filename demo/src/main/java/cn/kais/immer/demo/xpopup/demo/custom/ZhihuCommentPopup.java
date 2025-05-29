package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter4.BaseQuickAdapter;

import java.util.ArrayList;

import cn.kais.immer.demo.R;
import cn.kais.immer.demo.xpopup.demo.adapter.CommonAdapter;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.BasePopupView;
import cn.kais.immer.xpopup.core.BottomPopupView;
import cn.kais.immer.xpopup.interfaces.SimpleCallback;
import cn.kais.immer.xpopup.util.XPopupUtils;
import cn.kais.immer.xpopup.widget.VerticalRecyclerView;

/**
 * Description: 仿知乎底部评论弹窗
 * Create by dance, at 2018/12/25
 */
public class ZhihuCommentPopup extends BottomPopupView {

    VerticalRecyclerView recyclerView;
    private ArrayList<String> data;

//    private EasyAdapter<String> commonAdapter;

    private CommonAdapter mAdapter;

    public ZhihuCommentPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_temp).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出新的弹窗用来输入
                final CustomEditTextBottomPopup textBottomPopup = new CustomEditTextBottomPopup(getContext());
                new XPopup.Builder(getContext())
                        .autoOpenSoftInput(true)
                        .autoFocusEditText(true)
                        .setPopupCallback(new SimpleCallback() {
                            @Override
                            public void onShow(BasePopupView popupView) {
                            }

                            @Override
                            public void onDismiss(BasePopupView popupView) {
                                String comment = textBottomPopup.getComment();
                                if (!comment.isEmpty()) {
                                    data.add(0, comment);
                                    mAdapter.add(0, comment);
//                                    mCommonAdapter.notifyDataSetChanged();
                                }
                            }
                        })
                        .asCustom(textBottomPopup)
                        .show();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            data.add("这是一个自定义Bottom类型的弹窗！你可以在里面添加任何滚动的View，我已经智能处理好嵌套滚动，你只需编写UI和逻辑即可！");
        }

        mAdapter = new CommonAdapter(data);
        mAdapter.addOnItemChildClickListener(R.id.btnDel, new BaseQuickAdapter.OnItemChildClickListener<String>() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<String, ?> baseQuickAdapter, @NonNull View view, int i) {
                data.remove(i);
                mAdapter.removeAt(i);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<String>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<String, ?> baseQuickAdapter, @NonNull View view, int i) {
                System.out.println("点击");
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

//        commonAdapter = new EasyAdapter<String>(data, R.layout.adapter_zhihu_comment) {
//            @Override
//            protected void bind(@NonNull ViewHolder holder, @NonNull String s, final int position) {
//                holder.setText(R.id.name, "知乎大神 - " + position).setText(R.id.comment, s);
//                holder.getView(R.id.btnDel).setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        data.remove(position);
//                        commonAdapter.notifyItemRemoved(position);
//                        commonAdapter.notifyItemRangeChanged(position, data.size());
//                    }
//                });
//            }
//        };
//        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public boolean onItemLongClick(@NonNull View view, @NonNull RecyclerView.ViewHolder viewHolder, int i) {
//                return false;
//            }
//
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                //不要直接这样做，会导致消失动画未执行完就跳转界面，不流畅。
////                dismiss();
////                getContext().startActivity(new Intent(getContext(), DemoActivity.class))
//                //可以等消失动画执行完毕再开启新界面
////                dismissWith(new Runnable() {
////                    @Override
////                    public void run() {
////                        getContext().startActivity(new Intent(getContext(), DemoActivity.class));
////                    }
////                });
//            }
//        });
//        recyclerView.setAdapter(commonAdapter);
    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag", "知乎评论 onShow");
    }

    //完全消失执行
    @Override
    protected void onDismiss() {
        Log.e("tag", "知乎评论 onDismiss");
    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getScreenHeight(getContext()) * .7f);
    }

    @Override
    protected boolean onBackPressed() {
        Toast.makeText(getContext(), "拦截返回", Toast.LENGTH_SHORT).show();
        return true;
    }

}