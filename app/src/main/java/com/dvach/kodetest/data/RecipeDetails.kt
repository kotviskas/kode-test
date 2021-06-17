package com.dvach.kodetest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_details")
data class RecipeDetails(
    @PrimaryKey
    val uuid: String = "",
    val name: String = "",
    val images: ArrayList<String> = ArrayList(),
    val lastUpdated: Int = 0,
    val description: String = "",
    var instructions: String = "",
    var difficulty: Int = 0,
    val similar: ArrayList<RecipeBrief> = ArrayList()
)