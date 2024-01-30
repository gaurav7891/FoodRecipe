package com.nativeworks.foodrecipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nativeworks.foodrecipe.model.FoodRecipe
import com.nativeworks.foodrecipe.util.Constants

@Entity(tableName = Constants.RECIPES_TABLES)
class RecipesEntity(var foodRecipe: FoodRecipe) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}