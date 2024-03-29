package com.nativeworks.foodrecipe.util

object Constants {

    const val API_KEY = "999160f824a4440eb2ec4fe139a03069"
    const val BASE_URL = "https://api.spoonacular.com"
    const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

    // API Query keys
    const val QUERY_SEARCH = "query"
    const val QUERY_NUMBER = "number"
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_TYPE = "type"
    const val QUERY_DIET = "diet"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"

    // Room database
    const val DATABASE_NAME = "recipes_database"
    const val RECIPES_TABLES = "recipes_table"
    const val FAVORITES_TABLES = "favorites_table"
    const val FOOD_JOKE_TABLES = "food_joke_table"

    // Bottom Sheet and Preferences
    const val DEFAULT_RECIPES_NUMBER = "50"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"
    const val PREFERENCES_NAME = "food_preferences"
    const val PREFERENCES_MEAL_TYPE = "mealType"
    const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
    const val PREFERENCES_DIET_TYPE = "dietType"
    const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
    const val PREFERENCES_BACK_ONLINE = "backOnline"

}