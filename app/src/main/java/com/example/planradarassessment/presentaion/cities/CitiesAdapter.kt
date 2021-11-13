package com.example.planradarassessment.presentaion.cities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planradarassessment.databinding.ItemCityBinding
import com.example.planradarassessment.domain.model.City

class CitiesAdapter(private val items: MutableList<City>) : RecyclerView.Adapter<CitiesAdapter.CityVH>() {

    var onCityClick: ((City) -> Unit)? = null
    var onHistoryClick: ((City) -> Unit)? = null

    inner class CityVH(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: City) {
            binding.tvCityName.text = item.name
            binding.tvCityName.setOnClickListener {
                onCityClick?.invoke(item)
            }
            binding.ivHistory.setOnClickListener {
                onHistoryClick?.invoke(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityVH {
        return CityVH(ItemCityBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CityVH, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addCity(city: City){
        if(items.contains(city).not()){
            items.add(0,city)
            notifyItemInserted(0)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(cities: List<City>){
        items.addAll(0,cities)
        notifyDataSetChanged()
    }


}