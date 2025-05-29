package cn.kais.immer.demo.xpopup.demo.custom;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import cn.kais.immer.demo.R;
import cn.kais.immer.demo.xpopup.demo.adapter.CustomDrawerAdapter;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.DrawerPopupView;
import cn.kais.immer.xpopup.interfaces.OnConfirmListener;
import cn.kais.immer.xpopup.widget.VerticalRecyclerView;

/**
 * Description: 自定义抽屉弹窗
 * Create by dance, at 2018/12/20
 */
public class CustomDrawerPopupView extends DrawerPopupView {

//    TextView text;

    public CustomDrawerPopupView(@NonNull Context context) {
        super(context);
    }

    public CustomDrawerAdapter mAdapter;

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_drawer_popup2;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
//        CustomDrawerPopup2Binding.bind(getPopupImplView());
        Log.e("tag", "CustomDrawerPopupView onCreate");
//        text = findViewById(R.id.text);
//        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        //通过设置topMargin，可以让Drawer弹窗进行局部阴影展示
//        setPadding(0, 400, 0, 0);

        VerticalRecyclerView rv = findViewById(R.id.rv);
        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < 200; i++) {
            list.add(i + "");
        }

        mAdapter = new CustomDrawerAdapter(list);
        rv.setAdapter(mAdapter);

//        rv.setAdapter(new EasyAdapter(list, R.layout.temp) {
//            @Override
//            protected void bind(ViewHolder viewHolder, Object o, int i) {
//                if (i % 2 == 0) {
//                    viewHolder.<TextView>getView(R.id.text).setText("aa - " + i);
//                    viewHolder.<TextView>getView(R.id.text).setBackgroundColor(Color.WHITE);
//                } else {
//                    viewHolder.<TextView>getView(R.id.text).setText("aa - " + i + "大萨达所撒多" +
//                            "\n大萨达所撒多大萨达所撒多");
//                    viewHolder.<TextView>getView(R.id.text).setBackgroundColor(Color.RED);
//
//                }
//            }
//        });

        findViewById(R.id.btnMe).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new XPopup.Builder(getContext()).isDestroyOnDismiss(true)
                        .asConfirm("提示", "确定要退出吗？", new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                ((Activity) getContext()).finish();
                                dismiss();
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onShow() {
        super.onShow();
//        text.setText(new Random().nextInt()+"");
        Log.e("tag", "CustomDrawerPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag", "CustomDrawerPopupView onDismiss");
    }
}