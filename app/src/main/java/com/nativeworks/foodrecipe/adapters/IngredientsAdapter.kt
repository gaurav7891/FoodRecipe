package com.nativeworks.foodrecipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nativeworks.foodrecipe.R
import com.nativeworks.foodrecipe.databinding.IngredientRowLayoutBinding
import com.nativeworks.foodrecipe.model.ExtendedIngredient
import com.nativeworks.foodrecipe.util.Constants
import com.nativeworks.foodrecipe.util.RecipeDiffUtil

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientList = emptyList<ExtendedIngredient>()

    class MyViewHolder(private val binding: IngredientRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: ExtendedIngredient) {
            binding.imgIngredient.load(Constants.BASE_IMAGE_URL + ingredient.image){
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            binding.txtIngredientName.text = ingredient.name.capitalize()
            binding.txtIngredientAmount.text = ingredient.amount.toString()
            binding.ingredientUnit.text = ingredient.unit
            binding.txtIngredientConsistency.text = ingredient.consistency
            binding.txtIngredientOriginal.text = ingredient.original
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.bind(ingredient)
    }

    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientRecipeDiffUtil = RecipeDiffUtil(ingredientList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientRecipeDiffUtil)
        ingredientList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }


}