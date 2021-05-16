package com.example.androidtest.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.ItemBinding

import com.example.test.models.AQMResponseItem
import com.example.test.utility.CoreUtil

class AqiListAdapter(
    private val context: Context,
    private var list: ArrayList<AQMResponseItem>,
    val listner: AqiListAdapter.AdapterListner
) :
    RecyclerView.Adapter<AqiListAdapter.ItemHolder>() {

    class ItemHolder(val mBinding: ItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(newList: ArrayList<AQMResponseItem>) {
        list = newList
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.mBinding.textView3.text = list[position].city
        holder.mBinding.textView4.text =
            String.format("%.2f", list[position].aqi).toDouble().toString()
        holder.mBinding.root.setBackgroundColor(
            ContextCompat.getColor(
                context,
                CoreUtil.getColorCode(list[position].aqi)
            )
        )//context.resources.getColor(R.color.white)
        holder.mBinding.root.setOnClickListener {
            listner.onClick(list[position].city)
        }

    }

    interface AdapterListner {
        fun onClick(cityName: String)
    }
}