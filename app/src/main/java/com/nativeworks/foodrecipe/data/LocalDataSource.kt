package com.nativeworks.foodrecipe.data

import com.nativeworks.foodrecipe.data.database.RecipesDao
import com.nativeworks.foodrecipe.data.database.entities.FavoritesEntity
import com.nativeworks.foodrecipe.data.database.entities.FoodJokeEntity
import com.nativeworks.foodrecipe.data.database.entities.RecipesEntity
import com.nativeworks.foodrecipe.model.FoodJoke
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao) {

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readFavoritesRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoritesRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipes(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

    suspend fun insertFoodJoke(foodJoke: FoodJokeEntity){
        recipesDao.insertFoodJoke(foodJoke)
    }

    fun readFoodJoke():Flow<List<FoodJokeEntity>>{
        return recipesDao.readFoodJoke()
    }
}