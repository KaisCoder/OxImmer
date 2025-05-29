package cn.kais.immer.demo.xpopup.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;

import java.util.List;

public class ListDrawerAdapter extends BaseQuickAdapter<String, QuickViewHolder> {

    public ListDrawerAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable String s) {
        holder.setText(android.R.id.text1, s);
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(android.R.layout.simple_list_item_1, viewGroup);
    }
}
