package com.dvach.kodetest.ui.recipeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dvach.kodetest.data.Recipe
import com.dvach.kodetest.data.RecipesModel
import com.dvach.kodetest.data.SortOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipesListViewModel(private val model: RecipesModel) : ViewModel() {
    private var _recipes: MutableLiveData<List<Recipe>> = MutableLiveData(ArrayList())
    var recipes: LiveData<List<Recipe>> = _recipes
    private var sortOption = SortOptions.none
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isLoading: LiveData<Boolean> = _isLoading

    private lateinit var recipeList: List<Recipe>

    init {
        getRecipeList()
    }

    private fun getRecipeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            recipeList = model.getRecipesList()
            _recipes.postValue(recipeList)
            if (sortOption != SortOptions.none) {
                sortRecipes()
            }
            _isLoading.postValue(false)
        }
    }

    fun refreshRecipeList(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            recipeList = model.getRecipesList()
            _recipes.postValue(recipeList)
            withContext(Dispatchers.Main) {
                searchRecipes(text)
            }
            if (sortOption != SortOptions.none) {
                sortRecipes()
            }
            _isLoading.postValue(false)
        }
    }

    fun sortRecipes() {
        if (sortOption != SortOptions.none) {
            _recipes.postValue(if (sortOption == SortOptions.byName) {
                _recipes.value?.sortedBy { it.name }
            } else {
                _recipes.value?.sortedBy { it.lastUpdated }
            })
        }
    }

    fun changeSortOptionValue() {
        sortOption = if (sortOption == SortOptions.byName) {
            SortOptions.byDate
        } else {
            SortOptions.byName
        }
    }


    fun searchRecipes(searchWord: String) {

        _recipes.value = recipeList.filter {
            if (it.name != null) {
                it.name.contains(searchWord, true)
            } else {
                false
            } || if (it.description != null) {
                it.description.contains(searchWord, true)
            } else {
                false
            } || if (it.instructions != null) {
                it.instructions.contains(searchWord, true)
            } else {
                false
            }
        }

    }


}