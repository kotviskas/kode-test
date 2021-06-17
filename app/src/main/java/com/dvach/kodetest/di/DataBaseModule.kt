package com.dvach.kodetest.di

import androidx.room.Room
import com.dvach.kodetest.data.database.RecipeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            RecipeDatabase::class.java,
            "app_database"
        ).build()
    }
    single { get<RecipeDatabase>().RecipeDAO() }
    single { get<RecipeDatabase>().RecipeDetailsDAO() }
}