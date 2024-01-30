package com.nativeworks.foodrecipe.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nativeworks.foodrecipe.model.Result
import com.nativeworks.foodrecipe.util.Constants

@Entity(tableName = Constants.FAVORITES_TABLES)
class FavoritesEntity(@PrimaryKey(autoGenerate = true) var id: Int, var result: Result)