package com.nativeworks.foodrecipe.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.nativeworks.foodrecipe.R
import com.nativeworks.foodrecipe.model.Result
import com.nativeworks.foodrecipe.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup

class RecipesRowBinding {

    companion object {

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result) {
            Log.d("onRecipeClickListener", "CALLED")
            recipeRowLayout.setOnClickListener {
                try {
                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is TextView -> {
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }

                    is ImageView -> {
                        view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                    }
                }
            }
        }

        @BindingAdapter("parseHTML")
        @JvmStatic
        fun parseHTML(textView: TextView, description: String?) {
            description?.let {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }
        }
    }
}