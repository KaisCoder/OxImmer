package cn.kais.immer.xpopup.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.kais.immer.xpopup.core.BasePopupView;
import cn.kais.immer.xpopup.interfaces.OnClickOutsideListener;
import cn.kais.immer.xpopup.util.XPopupUtils;

import java.util.ArrayList;

/**
 * Description:
 * Create by dance, at 2019/1/10
 */
public class PartShadowContainer extends FrameLayout {
    public ArrayList<Rect> notDismissArea;
    public BasePopupView popupView;

    public PartShadowContainer(@NonNull Context context) {
        super(context);
    }

    public PartShadowContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PartShadowContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float x, y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 计算implView的Rect
        View implView = getChildAt(0);
        int[] location = new int[2];
        implView.getLocationInWindow(location);
        Rect implViewRect = new Rect(location[0], location[1], location[0] + implView.getMeasuredWidth(),
                location[1] + implView.getMeasuredHeight());
        if (!XPopupUtils.isInRect(event.getRawX(), event.getRawY(), implViewRect)) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    y = event.getY();
                    //TODO: PartShadowContainer需要实现自己的isClickThrough和isTouchThrough
//                    if(popupView!=null ){
//                        popupView.passTouchThrough(event);
//                    }
                    break;
                case MotionEvent.ACTION_MOVE:
//                    if(popupView!=null && popupView.popupInfo != null && popupView.popupInfo.isTouchThrough){
//                        popupView.passTouchThrough(event);
//                    }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    float dx = event.getX() - x;
                    float dy = event.getY() - y;
                    float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
                    if (distance < ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                        if (notDismissArea != null && !notDismissArea.isEmpty()) {
                            boolean inRect = false;
                            for (Rect r : notDismissArea) {
                                if (XPopupUtils.isInRect(event.getRawX(), event.getRawY(), r)) {
                                    inRect = true;
                                    break;
                                }
                            }
                            if (!inRect && listener != null) {
                                listener.onClickOutside();
                            }
                        } else {
                            if (listener != null) listener.onClickOutside();
                        }
                    }
                    x = 0;
                    y = 0;
                    break;
            }
        }
        return true;
    }

    private OnClickOutsideListener listener;

    public void setOnClickOutsideListener(OnClickOutsideListener listener) {
        this.listener = listener;
    }
}
