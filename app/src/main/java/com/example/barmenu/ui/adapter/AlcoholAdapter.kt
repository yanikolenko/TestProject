package com.example.barmenu.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barmenu.databinding.ItemAlcoholRecyclerBinding
import com.example.barmenu.tools.DisplayBitmap
import com.example.barmenu.ui.model.DrinkItem

class AlcoholAdapter(val clickListener: (String) -> Unit): RecyclerView.Adapter<AlcoholAdapter.MyViewHolder>() {

    private var data = mutableListOf<DrinkItem>()
    val displayBitmap = DisplayBitmap()

    @SuppressLint("NotifyDataSetChanged")
    fun setInfo(data: List<DrinkItem>){
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemAlcoholRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.setUiInfo(data[position])

        holder.itemView.setOnClickListener{
            clickListener(data[position].idDrink)
        }
    }

    override fun getItemCount() = data.size

    inner class MyViewHolder(var binding: ItemAlcoholRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setUiInfo(cocktailsDvo: DrinkItem){
            binding.alcoholName.text = cocktailsDvo.strDrink
            binding.alcoholType.text = cocktailsDvo.idDrink
            displayBitmap.displayPhoto(cocktailsDvo.strDrinkThumb, binding.imageView)

        }
    }
}