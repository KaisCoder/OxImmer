package cn.kais.immer.demo.xpopup.demo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;

import java.util.List;

import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.interfaces.OnSelectListener;

public class PartShadowAdapter extends BaseQuickAdapter<String, QuickViewHolder> {

    public PartShadowAdapter(List<String> data) {
        super(data);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder holder, int i, @Nullable String s) {
//        holder.getConvertView().setBackgroundColor(Color.parseColor("#fafafa"));
        holder.setText(android.R.id.text1, "长按我试试 - " + i);
        holder.itemView.setBackgroundColor(Color.parseColor("#FFD08A"));
        final XPopup.Builder builder = new XPopup.Builder(getContext()).hasShadowBg(false).watchView(holder.itemView);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                builder.asAttachList(new String[]{"置顶", "编辑", "删除"}, null, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
//                        toast(text);
                        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    }
                }).show();
                return true;
            }
        });
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(android.R.layout.simple_list_item_1, viewGroup);
    }
}
