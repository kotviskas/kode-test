package com.dvach.kodetest.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dvach.kodetest.data.RecipeBrief
import com.dvach.kodetest.databinding.SimilarRecipeLayoutBinding


class RecipeDetailsRecyclerAdapter(
    var listItems: ArrayList<RecipeBrief>,
    var listener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun update(list: ArrayList<RecipeBrief>) {
        listItems = list
        notifyDataSetChanged()
    }

    interface OnItemClick {
        fun itemClick(recipe: RecipeBrief)
    }

    class ItemViewHolder(val binding: SimilarRecipeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeBrief) {
            binding.recipeBrief = recipe
            binding.nameSimTextView.text = recipe.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            SimilarRecipeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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