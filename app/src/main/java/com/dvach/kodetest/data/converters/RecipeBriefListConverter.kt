package com.dvach.kodetest.data.converters

import androidx.room.TypeConverter
import com.dvach.kodetest.data.RecipeBrief
import com.google.gson.Gson

class RecipeBriefListConverter {
    @TypeConverter
    fun listToJson(value: ArrayList<RecipeBrief>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        ArrayList<RecipeBrief>(Gson().fromJson(value, Array<RecipeBrief>::class.java).toList())
}