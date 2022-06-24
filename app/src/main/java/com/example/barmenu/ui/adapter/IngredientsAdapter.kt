package com.example.barmenu.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barmenu.databinding.ItemIngredientsBinding
import com.example.barmenu.tools.DisplayBitmap

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    var data = mutableListOf<List<String>>()
    val displayBitmap = DisplayBitmap()

    @SuppressLint("NotifyDataSetChanged")
    fun setInfo(data: List<List<String>>){
        this.data += data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setUiInfo(data[position])
    }

    override fun getItemCount() = data.size

    inner class MyViewHolder(val binding: ItemIngredientsBinding): RecyclerView.ViewHolder(binding.root) {

        //data[0] - url
        //data[1] - text

        fun setUiInfo(data: List<String>){
            displayBitmap.displayPhoto(url = data[0], imageView = binding.ingredientsImageView)
            binding.nameOfIngredient.text = data[1]

        }
    }
}