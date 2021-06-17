package com.dvach.kodetest.ui.recipeslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.dvach.kodetest.databinding.FragmentRecipesListBinding
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvach.kodetest.R
import com.dvach.kodetest.ui.adapters.RecipesRecyclerAdapter
import com.dvach.kodetest.data.Recipe
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipesListFragment : Fragment(), RecipesRecyclerAdapter.OnItemClick {
    private lateinit var binding: FragmentRecipesListBinding
    private val viewModel by viewModel<RecipesListViewModel>()
    var adapterRecipe = RecipesRecyclerAdapter(ArrayList(), this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipes_list, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecycleParameters()

        viewModel.recipes.observe(viewLifecycleOwner, {
            adapterRecipe.update(it)
        })

        textChanged()

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = it
            if (it) {
                enableLoadingAnimation()
            } else {
                disableLoadingAnimation()
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshRecipeList()
        }
        binding.sortImageView.setOnClickListener {
            sortRecipes()
        }
    }

    private fun enableLoadingAnimation() {
        binding.lottieLayout.visibility = View.VISIBLE
        binding.lottieAnimationView.playAnimation()
    }

    private fun disableLoadingAnimation() {
        binding.lottieLayout.visibility = View.INVISIBLE
        binding.lottieAnimationView.pauseAnimation()
    }

    private fun refreshRecipeList() {
        viewModel.refreshRecipeList(binding.searchView.query.toString())
    }

    private fun sortRecipes() {
        viewModel.changeSortOptionValue()
        viewModel.sortRecipes()
    }

    private fun textChanged() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchRecipes(newText)
                    viewModel.sortRecipes()
                }
                return true
            }

        })
    }

    private fun setRecycleParameters() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapterRecipe
    }


    private fun navigateToAboutRecipeFragment(id: String) {
        val action =
            RecipesListFragmentDirections.actionRecipesListFragmentToAboutRecipeFragment(id)
        findNavController().navigate(action)
    }

    override fun itemClick(recipe: Recipe) {
        navigateToAboutRecipeFragment(recipe.uuid)
    }
}