package cn.kais.immer.xpopup.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import cn.kais.immer.xpopup.util.XPopupUtils;

/**
 * Description: 对勾View
 * Create by dance, at 2018/12/21
 */
public class CheckView extends View {
    Paint paint;
    int color = Color.TRANSPARENT;

    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(XPopupUtils.dp2px(context, 2));
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 设置对勾View
     *
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        postInvalidate();
    }

    Path path = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (color == Color.TRANSPARENT) return;
        // first part
        path.moveTo(getMeasuredWidth() / 4f, getMeasuredHeight() / 2f);
        path.lineTo(getMeasuredWidth() / 2f, getMeasuredHeight() * 3 / 4f);
        // second part
        path.lineTo(getMeasuredWidth(), getMeasuredHeight() / 4f);
        canvas.drawPath(path, paint);
    }
}
