package com.dvach.kodetest.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class ImagesListConverter {
    @TypeConverter
    fun listToJson(value: ArrayList<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        ArrayList<String>(Gson().fromJson(value, Array<String>::class.java).toList())
}