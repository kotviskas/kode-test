package com.dvach.kodetest.ui.aboutrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dvach.kodetest.data.RecipeDetails
import com.dvach.kodetest.data.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AboutRecipeViewModel(private val model: RecipesModel) : ViewModel() {
    private var _recipe: MutableLiveData<RecipeDetails> = MutableLiveData<RecipeDetails>()
    var recipe: LiveData<RecipeDetails> = _recipe
    private var _isError: MutableLiveData<Boolean> = MutableLiveData()
    var isError: LiveData<Boolean> = _isError
    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isLoading: LiveData<Boolean> = _isLoading

    fun getRecipe(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _isError.postValue(false)
            val recipeDetails = model.getRecipe(id)
            if (recipeDetails != null) {
                recipeDetails.instructions = recipeDetails.instructions.replace("<br>", "\n")
                _recipe.postValue(recipeDetails)
            } else {
                _isError.postValue(true)
            }
            _isLoading.postValue(false)
        }
    }
}