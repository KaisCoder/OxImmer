package cn.kais.immer.demo.xpopup.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;

import java.util.List;

import cn.kais.immer.demo.R;

public class ViewPager3Adapter extends BaseQuickAdapter<Object, QuickViewHolder> {

    public ViewPager3Adapter(List<Object> data) {
        super(data);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable Object o) {
        final ImageView imageView = holder.getView(R.id.image);
        //1. 加载图片
        Glide.with(imageView).load(o).error(R.mipmap.ic_launcher).into(imageView);
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.adapter_image2, viewGroup);
    }
}
