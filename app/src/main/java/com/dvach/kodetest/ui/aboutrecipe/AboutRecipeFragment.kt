package com.dvach.kodetest.ui.aboutrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvach.kodetest.R
import com.dvach.kodetest.ui.adapters.PhotosSliderAdapter
import com.dvach.kodetest.ui.adapters.RecipeDetailsRecyclerAdapter
import com.dvach.kodetest.data.RecipeBrief
import com.dvach.kodetest.databinding.FragmentAboutRecipeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AboutRecipeFragment : Fragment(), RecipeDetailsRecyclerAdapter.OnItemClick,
    PhotosSliderAdapter.OnPhotoClick {
    private lateinit var binding: FragmentAboutRecipeBinding
    private val viewModel by viewModel<AboutRecipeViewModel>()
    private var adapterSimilarRecipes = RecipeDetailsRecyclerAdapter(ArrayList(), this)
    var adapterPhoto: PhotosSliderAdapter = PhotosSliderAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val id = AboutRecipeFragmentArgs.fromBundle(it).id
            viewModel.getRecipe(id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_about_recipe, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecycleParameters(adapterSimilarRecipes)
        viewModel.recipe.observe(viewLifecycleOwner, {
            adapterPhoto.renewItems(it.images)
            adapterSimilarRecipes.update(it.similar)
            binding.apply {
                binding.recipe = it
                simpleRatingBar.rating = it.difficulty.toFloat()
                imageSlider.setSliderAdapter(adapterPhoto)
                imageSlider.setInfiniteAdapterEnabled(false)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it) {
                enableLoadingAnimation()
            } else {
                disableLoadingAnimation()
            }
        })
        viewModel.isError.observe(viewLifecycleOwner, {
            if (it) {
                enableErrorAnimation()
            } else {
                disableErrorAnimation()
            }
        })
    }

    private fun enableErrorAnimation() {
        binding.scrollView2.visibility = View.INVISIBLE
        binding.lottieAnimationView2.visibility = View.VISIBLE
        binding.lottieAnimationView2.playAnimation()
    }

    private fun disableErrorAnimation() {
        binding.scrollView2.visibility = View.VISIBLE
        binding.lottieAnimationView2.visibility = View.INVISIBLE
        binding.lottieAnimationView2.pauseAnimation()
    }

    private fun enableLoadingAnimation() {
        binding.scrollView2.visibility = View.INVISIBLE
        binding.lottieAnimationView4.visibility = View.VISIBLE
        binding.lottieAnimationView4.playAnimation()
    }

    private fun disableLoadingAnimation() {
        binding.scrollView2.visibility = View.VISIBLE
        binding.lottieAnimationView4.visibility = View.INVISIBLE
        binding.lottieAnimationView4.pauseAnimation()
    }

    private fun setRecycleParameters(adapterRecipe: RecipeDetailsRecyclerAdapter) {
        binding.similarRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.similarRecyclerView.adapter = adapterRecipe
    }


    private fun navigateToSimilarRecipe(id: String) {
        val action =
            AboutRecipeFragmentDirections.actionAboutRecipeFragmentSelf(id)
        findNavController().navigate(action)
    }

    private fun navigateToRecipePhoto(url: String) {
        val action =
            AboutRecipeFragmentDirections.actionAboutRecipeFragmentToRecipePhotoFragment(url)
        findNavController().navigate(action)
    }

    override fun itemClick(recipe: RecipeBrief) {
        navigateToSimilarRecipe(recipe.uuid)
    }

    override fun itemClick(url: String) {
        navigateToRecipePhoto(url)
    }


}