package com.dvach.kodetest.data

import androidx.room.*

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val images: ArrayList<String>,
    val lastUpdated: Int,
    val description: String?,
    val instructions: String?,
    val difficulty: Int,
)

