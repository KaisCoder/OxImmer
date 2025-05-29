package cn.kais.immer.demo.adapter

import android.content.Context
import android.view.ViewGroup
import cn.kais.immer.demo.R
import cn.kais.immer.demo.bean.BtnItem
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder

class MainAdapter(data: ArrayList<BtnItem>) : BaseQuickAdapter<BtnItem, QuickViewHolder>(data) {

    //    class VH(var binding: ItemHeaderAndFooterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: BtnItem?) {
        holder.setText(R.id.btn, item!!.name)
        holder.itemView.setOnClickListener {  }
    }

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): QuickViewHolder {
        return QuickViewHolder(R.layout.adapter_main_item, parent)
    }

}