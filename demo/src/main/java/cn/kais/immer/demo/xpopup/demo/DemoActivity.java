package cn.kais.immer.demo.xpopup.demo;

import static cn.kais.immer.demo.xpopup.demo.Constants.list;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import cn.kais.immer.demo.R;
import cn.kais.immer.demo.xpopup.demo.adapter.ImageViewerAdapter;
import cn.kais.immer.demo.xpopup.demo.fragment.FragmentLifecycleDemo;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.BasePopupView;
import cn.kais.immer.xpopup.enums.PopupAnimation;
import cn.kais.immer.xpopup.interfaces.OnConfirmListener;
import cn.kais.immer.xpopup.interfaces.OnSelectListener;

/**
 * Description:
 * Create by lxj, at 2019/2/2
 */
public class DemoActivity extends AppCompatActivity {

    EditText editText;
    RecyclerView recyclerView;
    BasePopupView attachPopup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        editText = findViewById(R.id.et);
        recyclerView = findViewById(R.id.recyclerView);
        findViewById(R.id.btnShowFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment();
            }
        });
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiPopup();
            }
        });
        showMultiPopup();

        attachPopup = new XPopup.Builder(this)
                .atView(editText)
                .dismissOnTouchOutside(false)
                .isViewMode(true)      //开启View实现
                .isRequestFocus(false) //不强制焦点
                .isTouchThrough(true)
                .hasShadowBg(false)
                .positionByWindowCenter(true)
                .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
                .asAttachList(new String[]{"联想到的内容 - 1", "联想到的内容 - 2", "联想到的内容 - 333"}, null, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
                    }
                });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    attachPopup.dismiss();
                    return;
                }
                if (attachPopup.isDismiss()) {
                    attachPopup.show();
                }
            }
        });

        initData();
    }


    static {
        list.clear();
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2279952540,2544282724&fm=26&gp=0.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=851052518,4050485518&fm=26&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=174904559,2874238085&fm=26&gp=0.jpg");
        list.add("https://image.flaticon.com/icons/png/512/910/910277.png");
        list.add("https://user-gold-cdn.xitu.io/2019/1/25/168839e977414cc1?imageView2/2/w/800/q/100");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1551692956639&di=8ee41e070c6a42addfc07522fda3b6c8&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160413%2F75659e9b05b04eb8adf5b52669394897.jpg");
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.love.tv%2F2017%2F10%2F15%2F23%2F3dc47bd3b80d4dfc89cfc8d74a0c44fe.gif&refer=http%3A%2F%2Fimg.love.tv&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg");
        list.add("https://word.7english.cn/user/publicNoteImage/4e44a8706ee94016a4d40ad0693e9f41/B40CF2CA54715E64CF4AA3632FD4F70E.jpg");
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.huabanimg.com%2F3fee54d0b2e0b7a132319a8e104f5fdc2edd3d35d03ee-93Jmdq_fw658&refer=http%3A%2F%2Fhbimg.huabanimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg");
        list.add("https://word.7english.cn/user/publicNoteImage/4e44a8706ee94016a4d40ad0693e9f41/3F8B1BFDCBA2559EB69BA1670915E912.jpg");
        list.add("https://word.7english.cn/user/publicNoteImage/4e44a8706ee94016a4d40ad0693e9f41/5C50B56D6FC9C30562FE15716B02AA3E.jpg");
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F9f569629c4dec5ed1b603982058c6853607b1f0af685e-PcenmQ_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg");
        list.add("https://test.yujoy.com.cn:59010/file/postImage/2021/03/03/7c9114bb-bc4a-40c4-94ab-01833228f26f.png");
        list.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.baidu.com%2F94o3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F8c1001e93901213f1820a0d956e736d12f2e95a0.jpg&refer=http%3A%2F%2Fgss0.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg");
        list.add("https://img.live.qiqushiting.com/Pimg/img/squ/img/107821622429832886.jpg");
        list.add("http://moimg0.mwim.store/image/5BE8A5CF4893D9197D6D6D66BE294488.jpg");
        list.add("http://cfile.frees.fun/picwall/796804C4A25DE27342A2A0987283AB03.jpg");
        list.add("http://test-yjk.oss-cn-chengdu.aliyuncs.com/APP/dynamic/picture/1637229940713.jpeg");
        list.add("https://gb-small.voopoo.com.cn/voopoo-retail-gb/portrait/202210/OIP-C.jpg");
    }

    private void initData() {
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setAdapter(new ImageViewerDemo.ImageAdapter());
        recyclerView.setAdapter(new ImageViewerAdapter(list));
        showFragment();
    }

    public void showMultiPopup() {
        final BasePopupView loadingPopup = new XPopup.Builder(this).asLoading();
        loadingPopup.show();
        new XPopup.Builder(DemoActivity.this)
                .autoDismiss(false)
                .asBottomList("haha", new String[]{"点我显示弹窗", "点我显示弹窗", "点我显示弹窗", "点我显示弹窗"}, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        new XPopup.Builder(DemoActivity.this).asConfirm("测试", "aaaa", new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                loadingPopup.dismiss();
                            }
                        }).show();
                    }
                }).show();


    }

    FragmentLifecycleDemo fragmentLifecycleDemo;

    public void showFragment() {
        fragmentLifecycleDemo = new FragmentLifecycleDemo();
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentLifecycleDemo)
                .commitNow();
    }

    private Handler handler = new Handler();

    public void delayDestroy() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fragmentLifecycleDemo == null) return;
                getSupportFragmentManager().beginTransaction().remove(fragmentLifecycleDemo)
                        .commitNow();
                fragmentLifecycleDemo = null;
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
