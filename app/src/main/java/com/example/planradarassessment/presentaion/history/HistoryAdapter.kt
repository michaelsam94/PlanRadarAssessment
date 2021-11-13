package com.example.planradarassessment.presentaion.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.planradarassessment.common.Utils
import com.example.planradarassessment.databinding.ItemHistoryBinding
import com.example.planradarassessment.domain.model.History

class HistoryAdapter(private val items: MutableList<History>): RecyclerView.Adapter<HistoryAdapter.HistoryVH>() {

    inner class HistoryVH(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: History) {
            Glide.with(binding.ivCurrentCondition).load(item.icon).into(binding.ivCurrentCondition)
            binding.tvDate.text = Utils.convertTimeStamp(item.timeStamp)
            binding.tvConditionTemp.text = "${item.condition}, ${item.temp} °C"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        return HistoryVH(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(historyItems: List<History>){
        items.addAll(0,historyItems)
        notifyDataSetChanged()
    }
}