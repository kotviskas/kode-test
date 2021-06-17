package com.dvach.kodetest.ui.photo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import com.dvach.kodetest.R
import com.dvach.kodetest.databinding.FragmentRecipePhotoBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipePhotoFragment : Fragment() {
    private lateinit var binding: FragmentRecipePhotoBinding
    private val viewModel by viewModel<RecipePhotoViewModel>()
    var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = RecipePhotoFragmentArgs.fromBundle(it).url
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_photo, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(url).into(binding.photoSaveImageView)
        binding.lottieAnimationView2.setOnClickListener {
            context?.let { it1 ->
                viewModel.saveImage(
                    binding.photoSaveImageView.drawable.toBitmap(),
                    it1, requireActivity()
                )
            }
        }

        viewModel.isError.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), "Can't save photo", Toast.LENGTH_SHORT).show()
            }
        })
    }


}