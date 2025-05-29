package cn.kais.immer.xpopup.interfaces;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.kais.immer.xpopup.core.ImageViewerPopupView;
import cn.kais.immer.xpopup.photoview.PhotoView;

import java.io.File;

public interface XPopupImageLoader {

    void loadSnapshot(@NonNull Object uri, @NonNull PhotoView snapshot, @Nullable ImageView srcView);


    View loadImage(int position, @NonNull Object uri, @NonNull ImageViewerPopupView popupView, @NonNull PhotoView snapshot, @NonNull ProgressBar progressBar);

    /**
     * 获取图片对应的文件
     *
     * @param context
     * @param uri
     * @return
     */
    File getImageFile(@NonNull Context context, @NonNull Object uri);
}
