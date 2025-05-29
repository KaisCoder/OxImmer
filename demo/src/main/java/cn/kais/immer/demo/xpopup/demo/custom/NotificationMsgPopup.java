package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.core.PositionPopupView;

/**
 * Description: 自定义自由定位Position弹窗
 * Create by dance, at 2019/6/14
 */
public class NotificationMsgPopup extends PositionPopupView {

    int width;

    public NotificationMsgPopup(@NonNull Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return;
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        width = point.x;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_notification_msg;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tvClose).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected int getPopupWidth() {
//        return ScreenUtils.getScreenWidth();
        return width;
    }

}
