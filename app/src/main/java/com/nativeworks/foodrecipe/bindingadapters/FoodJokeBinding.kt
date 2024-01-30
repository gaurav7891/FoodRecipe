package com.nativeworks.foodrecipe.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.nativeworks.foodrecipe.data.database.entities.FoodJokeEntity
import com.nativeworks.foodrecipe.model.FoodJoke
import com.nativeworks.foodrecipe.util.NetworkResult

class FoodJokeBinding {

    companion object {
        @BindingAdapter("readApiResponse3", "readDatabase3", requireAll = false)
        @JvmStatic
        fun setCardAndProgressVisibility(
            view: View,
            readApiResponse: NetworkResult<FoodJoke>?,
            readDatabase: List<FoodJokeEntity>?
        ) {
            when (readApiResponse) {
                is NetworkResult.Loading -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.VISIBLE
                        }

                        is MaterialCardView -> {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }

                is NetworkResult.Error -> {

                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }

                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                            if (readDatabase != null) {
                                if (readDatabase.isEmpty()) {
                                    view.visibility = View.INVISIBLE
                                }
                            }
                        }
                    }

                }

                is NetworkResult.Success -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }

                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                        }
                    }
                }

                else -> {}
            }
        }

        @BindingAdapter("readApiResponse4", "readDatabase4", requireAll = true)
        @JvmStatic
        fun setErrorViewVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            if (database != null) {
                if (database.isEmpty()) {
                    view.visibility = View.VISIBLE
                    if (view is TextView) {
                        if (apiResponse != null) {
                            view.text = apiResponse.message.toString()
                        }
                    }
                }
            }
            if (apiResponse is NetworkResult.Success) {
                view.visibility = View.INVISIBLE
            }
        }
    }
}