package cn.kais.immer.demo.xpopup.demo.custom;

import android.content.Context;

import androidx.annotation.NonNull;

import cn.kais.immer.demo.R;
import cn.kais.immer.xpopup.core.PositionPopupView;
import cn.kais.immer.xpopup.enums.DragOrientation;

/**
 * Description: 自定义自由定位Position弹窗
 * Create by dance, at 2019/6/14
 */
public class QQMsgPopup extends PositionPopupView {

    public QQMsgPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_qq_msg;
    }

    @Override
    protected DragOrientation getDragOrientation() {
        return DragOrientation.DragToLeft;
    }

}
