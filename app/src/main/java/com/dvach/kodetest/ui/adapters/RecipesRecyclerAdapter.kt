package com.dvach.kodetest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dvach.kodetest.data.Recipe
import com.dvach.kodetest.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso


class RecipesRecyclerAdapter(
    var listItems: List<Recipe>,
    var listener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun update(list: List<Recipe>) {
        listItems = list
        notifyDataSetChanged()
    }


    interface OnItemClick {
        fun itemClick(recipe: Recipe)
    }

    class ItemViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            Picasso.get().load(recipe.images[0]).into(binding.imageView2)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return listItems.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(listItems[position])
        holder.binding.listener = listener
    }
}