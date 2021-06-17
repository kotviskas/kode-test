package com.dvach.kodetest.data

import android.util.Log
import com.dvach.kodetest.api.RepoApi
import com.dvach.kodetest.data.database.RecipeDatabase
import java.lang.Exception

class RecipesModel(private val repoApi: RepoApi, private val database: RecipeDatabase) {
    private var _recipes: ArrayList<Recipe> = ArrayList()
    private var recipe: RecipeDetails = RecipeDetails()

    suspend fun getRecipesList(): ArrayList<Recipe> {
        _recipes = try {
            val newRecipes = repoApi.getRecipesList()
            database.RecipeDAO().insertAll(newRecipes.recipes)
            newRecipes.recipes
        } catch (e: Exception) {
            Log.d("Error while get recipes", e.message.toString())
            database.RecipeDAO().getRecipesList() as ArrayList<Recipe>
        }
        return _recipes
    }

    suspend fun getRecipe(id: String): RecipeDetails {
        recipe = try {
            val newRecipe = repoApi.getRecipe(id)
            database.RecipeDetailsDAO().insert(newRecipe.recipe)
            newRecipe.recipe
        } catch (e: Exception) {
            Log.d("Error while get recipe", e.message.toString())
            database.RecipeDetailsDAO().getRecipe(id)
        }
        return recipe
    }

}