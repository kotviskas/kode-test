package com.dvach.kodetest.api

import com.dvach.kodetest.data.DTO.RecipeDTO
import com.dvach.kodetest.data.DTO.RecipeDetailsDTO
import retrofit2.http.*

interface RepoApi {

    @GET("/recipes")
    suspend fun getRecipesList(): RecipeDTO

    @GET("/recipes/{id}")
    suspend fun getRecipe(@Path("id") id: String): RecipeDetailsDTO

}