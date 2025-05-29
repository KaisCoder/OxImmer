package cn.kais.immer.demo.xpopup.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;

import java.util.List;

import cn.kais.immer.demo.R;

public class ImageViewerAdapter extends BaseQuickAdapter<Object, QuickViewHolder> {

    public ImageViewerAdapter(List<Object> data) {
        super(data);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable Object s) {
        ImageView imageView = holder.getView(R.id.image);
        //1. 加载图片
        Glide.with(imageView).load(s)
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(20)))
                .error(R.mipmap.ic_launcher).into(imageView);
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.adapter_image, viewGroup);
    }

}
