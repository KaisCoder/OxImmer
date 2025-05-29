package cn.kais.immer.xpopup.interfaces;

import androidx.annotation.NonNull;

import cn.kais.immer.xpopup.core.ImageViewerPopupView;

/**
 * Description:
 * Create by dance, at 2019/1/29
 */
public interface OnSrcViewUpdateListener {
    void onSrcViewUpdate(@NonNull ImageViewerPopupView popupView, int position);
}
