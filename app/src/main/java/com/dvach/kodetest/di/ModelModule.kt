package com.dvach.kodetest.di

import com.dvach.kodetest.data.RecipePhotoModel
import com.dvach.kodetest.data.RecipesModel
import org.koin.dsl.module

var modelModule = module {
    single { RecipesModel(get(), get()) }
    single { RecipePhotoModel() }
}