package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.core.CenterPopupView;
import cn.kais.immer.xpopup.interfaces.OnSelectListener;

public class LoginPopup extends CenterPopupView {

    public LoginPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_login;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        Button button = findViewById(R.id.btnSelect);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new XPopup.Builder(getContext())
                        .hasShadowBg(false)
                        .isRequestFocus(false)
                        .atView(v)
                        .asAttachList(new String[]{"1", "2", "3", "4"}, null, new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {

                            }
                        }).show();
            }
        });
        EditText etName = findViewById(R.id.etName);
        etName.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    Toast.makeText(getContext(), "按了删除键", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

}
