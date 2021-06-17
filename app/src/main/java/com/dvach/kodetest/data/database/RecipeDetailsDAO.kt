package com.dvach.kodetest.data.database

import androidx.room.*
import com.dvach.kodetest.data.RecipeDetails

@Dao
interface RecipeDetailsDAO {
    @Query("SELECT * FROM recipes_details WHERE uuid = :id")
    fun getRecipe(id: String): RecipeDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: RecipeDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: List<RecipeDetails>)

    @Delete
    fun delete(recipe: RecipeDetails)

    @Query("DELETE FROM recipes_details")
    fun deleteAll()
}