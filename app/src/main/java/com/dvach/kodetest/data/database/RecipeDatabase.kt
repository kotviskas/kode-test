package com.dvach.kodetest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dvach.kodetest.data.Recipe
import com.dvach.kodetest.data.RecipeDetails
import com.dvach.kodetest.data.converters.ImagesListConverter
import com.dvach.kodetest.data.converters.RecipeBriefListConverter

@Database(entities = [Recipe::class, RecipeDetails::class], version = 1)
@TypeConverters(ImagesListConverter::class, RecipeBriefListConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun RecipeDAO(): RecipeDAO
    abstract fun RecipeDetailsDAO(): RecipeDetailsDAO
}