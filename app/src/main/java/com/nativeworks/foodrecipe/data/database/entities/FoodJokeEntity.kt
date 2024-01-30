package com.nativeworks.foodrecipe.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nativeworks.foodrecipe.model.FoodJoke
import com.nativeworks.foodrecipe.util.Constants

@Entity(tableName = Constants.FOOD_JOKE_TABLES)
class FoodJokeEntity(@Embedded var foodJoke: FoodJoke) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}