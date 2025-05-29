package cn.kais.immer.xpopup.impl;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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
import cn.kais.immer.xpopup.XPopup;
import cn.kais.immer.xpopup.util.XPopupUtils;
import cn.kais.immer.xpopup.widget.CheckView;

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.BottomHolder> {

    Context context;
    int[] icons;
    String[] texts;
    int checkedPosition;

    public BottomAdapter(Context context, String[] texts, int[] icons, int checkedPosition) {
        this.context = context;
        this.texts = texts;
        this.icons = icons;
        this.checkedPosition = checkedPosition;
    }

    @Override
    public int getItemCount() {
        return texts.length;
    }

    @NonNull
    @Override
    public BottomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout._xpopup_adapter_text_match, parent, false);
        return new BottomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomHolder holder, @SuppressLint("RecyclerView") int position) {
        String text = texts[position];
        holder.textView.setText(text);
        System.out.println(text);

        if (icons != null && icons.length > position) {
            if (holder.imageView != null) {
                XPopupUtils.setVisible(holder.imageView, true);
                holder.imageView.setBackgroundResource(icons[position]);
            }
        } else {
            XPopupUtils.setVisible(holder.imageView, false);
        }

        if (checkedPosition != -1) {
            holder.checkView.setVisibility(position == checkedPosition ? VISIBLE : GONE);
            holder.checkView.setColor(XPopup.getPrimaryColor());
            holder.textView.setTextColor(position == checkedPosition ? XPopup.getPrimaryColor() : context.getResources().getColor(R.color._xpopup_title_color));
            holder.textView.setGravity(XPopupUtils.isLayoutRtl(context) ? Gravity.END : Gravity.START);
        } else {
            holder.checkView.setVisibility(GONE);
            holder.textView.setGravity(Gravity.CENTER);
        }

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

    public static class BottomHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public CheckView checkView;

        LinearLayout linearLayout;

        public BottomHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.tv_text);
            imageView = view.findViewById(R.id.iv_image);
            checkView = view.findViewById(R.id.check_view);
            linearLayout = itemView.findViewById(R.id._ll_temp);
        }
    }

    public interface AttachItemClickListener {
        void onClick(int index, String text);
    }

}
