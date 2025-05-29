package cn.kais.immer.demo.xpopup.demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;

import java.util.List;

import cn.kais.immer.demo.R;

public class CustomDrawerAdapter extends BaseQuickAdapter<String, QuickViewHolder> {

    public CustomDrawerAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable String s) {
        if (i % 2 == 0) {
            holder.setText(R.id.text, "aa - " + i);
            holder.setBackgroundColor(R.id.text, Color.WHITE);
        } else {
            holder.setText(R.id.text, "BB - " + i);
            holder.setBackgroundColor(R.id.text, Color.RED);
        }
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.temp, viewGroup);
    }

}
