package cn.kais.immer.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import cn.kais.immer.demo.databinding.FragmentRecyclerBinding;
import cn.kais.immer.java.UltimateBarX;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:19 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class RecyclerFragment extends InnerFragment {

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

//    private RecyclerView rvMain;

//    public RecyclerFragment() {
//        super(R.layout.fragment_recycler);
//    }


    private FragmentRecyclerBinding bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = FragmentRecyclerBinding.inflate(getLayoutInflater());
        return bind.getRoot();
    }
    //    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        var bind = FragmentImageTextBinding.inflate(layoutInflater)
//        return bind.root
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUltimateBarX();
//        rvMain = view.findViewById(R.id.rvMain);
        bind.rvMain.setLayoutManager(new LinearLayoutManager(requireContext()));
        bind.rvMain.setAdapter(new Adapter());
    }

    private void initUltimateBarX() {
        UltimateBarX.statusBar(this)
                .fitWindow(false)
                .light(false)
                .colorRes(R.color.alphaGreen)
                .apply();
        UltimateBarX.navigationBar(this)
                .fitWindow(false)
                .light(false)
                .colorRes(R.color.alphaBlack)
                .apply();
    }

    private static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv.setText(String.format(Locale.getDefault(), "九阴真经 %d", position));
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }

}
