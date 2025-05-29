package cn.kais.immer.demo.xpopup.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;

import java.util.List;

import cn.kais.immer.demo.R;

public class CommonAdapter extends BaseQuickAdapter<String, QuickViewHolder> {

    public CommonAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable String s) {
        holder.setText(R.id.name, "知乎大神啊 - " + i);
        holder.setText(R.id.comment, s);
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.adapter_zhihu_comment, viewGroup);
    }

}
