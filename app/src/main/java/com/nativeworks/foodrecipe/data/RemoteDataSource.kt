package com.nativeworks.foodrecipe.data

import com.nativeworks.foodrecipe.data.network.FoodRecipesApi
import com.nativeworks.foodrecipe.model.FoodJoke
import com.nativeworks.foodrecipe.model.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

    suspend fun getFoodJoke(apiKey:String):Response<FoodJoke>{
        return foodRecipesApi.getFoodJoke(apiKey)
    }
} 