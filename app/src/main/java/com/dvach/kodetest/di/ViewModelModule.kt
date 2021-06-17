package com.dvach.kodetest.di


import androidx.room.Room
import com.dvach.kodetest.api.RepoApi
import com.dvach.kodetest.ui.aboutrecipe.AboutRecipeViewModel
import com.dvach.kodetest.data.RecipePhotoModel
import com.dvach.kodetest.ui.photo.RecipePhotoViewModel
import com.dvach.kodetest.data.RecipesModel
import com.dvach.kodetest.data.database.RecipeDatabase
import com.dvach.kodetest.ui.recipeslist.RecipesListViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        RecipesListViewModel(get())
    }
    viewModel {
        AboutRecipeViewModel(get())
    }
    viewModel {
        RecipePhotoViewModel(get())
    }
}







