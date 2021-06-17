package com.dvach.kodetest.data.database

import androidx.room.*
import com.dvach.kodetest.data.Recipe

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipes")
    fun getRecipesList(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: List<Recipe>)

    @Delete
    fun delete(recipe: Recipe)

    @Query("DELETE FROM recipes")
    fun deleteAll()
}