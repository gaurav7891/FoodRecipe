package com.nativeworks.foodrecipe.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nativeworks.foodrecipe.util.Constants.DEFAULT_DIET_TYPE
import com.nativeworks.foodrecipe.util.Constants.DEFAULT_MEAL_TYPE
import com.nativeworks.foodrecipe.util.Constants.PREFERENCES_BACK_ONLINE
import com.nativeworks.foodrecipe.util.Constants.PREFERENCES_DIET_TYPE
import com.nativeworks.foodrecipe.util.Constants.PREFERENCES_DIET_TYPE_ID
import com.nativeworks.foodrecipe.util.Constants.PREFERENCES_MEAL_TYPE
import com.nativeworks.foodrecipe.util.Constants.PREFERENCES_MEAL_TYPE_ID
import com.nativeworks.foodrecipe.util.Constants.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        context.dataStore.edit { preferences ->
            run {
                preferences[PreferenceKeys.selectedMealType] = mealType
                preferences[PreferenceKeys.selectMealTypeId] = mealTypeId
                preferences[PreferenceKeys.selectedDietType] = dietType
                preferences[PreferenceKeys.selectDietTypeId] = dietTypeId
            }
        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    fun readBackOnline(): Flow<Boolean> = context.dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }.map { pref ->
            val backOnline = pref[PreferenceKeys.backOnline] ?: false
            backOnline
        }

    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }.map { pref ->
            val selectedMealType = pref[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = pref[PreferenceKeys.selectMealTypeId] ?: 0
            val selectedDietType = pref[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = pref[PreferenceKeys.selectDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }
}

data class MealAndDietType(
    val selectedMealType: String, val selectedMealTypeId: Int,
    val selectedDietType: String, val selectedDietTypeId: Int
)