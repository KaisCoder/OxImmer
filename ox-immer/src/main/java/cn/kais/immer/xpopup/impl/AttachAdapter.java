package cn.kais.immer.xpopup.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.kais.immer.R;
import cn.kais.immer.xpopup.util.XPopupUtils;

public class AttachAdapter extends RecyclerView.Adapter<AttachAdapter.AttachHolder> {

    Context context;
    int[] icons;
    String[] texts;

    public AttachAdapter(Context context, String[] texts, int[] icons) {
        this.context = context;
        this.texts = texts;
        this.icons = icons;
    }

    @Override
    public int getItemCount() {
        return texts.length;
    }

    @NonNull
    @Override
    public AttachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout._xpopup_adapter_text, parent, false);
        return new AttachHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttachHolder holder, @SuppressLint("RecyclerView") int position) {
        String text = texts[position];
        holder.textView.setText(text);

        if (icons != null && icons.length > position) {
            if (holder.imageView != null) {
                XPopupUtils.setVisible(holder.imageView, true);
                holder.imageView.setBackgroundResource(icons[position]);
            }
        } else {
            XPopupUtils.setVisible(holder.imageView, false);
        }

        holder.linearLayout.setGravity(Gravity.CENTER);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onClick(position, text);
                }
            }
        });

    }

    public AttachItemClickListener listener = null;

    public void setOnClickListener(AttachItemClickListener listener) {
        this.listener = listener;
    }

    public static class AttachHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        LinearLayout linearLayout;

        public AttachHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_text);
            imageView = itemView.findViewById(R.id.iv_image);
            linearLayout = itemView.findViewById(R.id._ll_temp);
        }
    }

    public interface AttachItemClickListener {
        void onClick(int index, String text);
    }


}
