package com.fitness.app.views.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.UserCommentAdapter
import com.fitness.app.adapters.RecipeReviewsAdapter
import com.fitness.app.databinding.FragmentAthleteDietRecipeDescriptionBinding
import com.fitness.app.util.constructRecipeIngredients
import com.fitness.app.viewmodel.AthleteHomeViewModel

class AthleteDietRecipeDescriptionFragment : Fragment(R.layout.fragment_athlete_diet_recipe_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDietRecipeDescriptionBinding
    lateinit var recipeReviewsAdapter: RecipeReviewsAdapter

    var recipeId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteDietRecipeDescriptionBinding.bind(view)

        val bundle = this.arguments
        if (bundle != null) {
            recipeId = bundle.get("recipeId").toString()
        }

        Log.e("recipeId",recipeId)

        setUpRecipeData()
        setUpRecipeReviews()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }

    }

    private fun setUpRecipeData() {
        viewModel.getRecipe(recipeId = recipeId,context = requireContext()){ recipe->
            binding.recipeCoverPicture.background = BitmapDrawable(resources,recipe.image)
            binding.recipeTitle.text = recipe.title
            binding.recipeDescription.text = recipe.description
            binding.recipeOrigin.text = recipe.origin
            binding.recipePrepTimeInMinutes.text = recipe.prepTimeInMinutes.toString()
            binding.recipeServings.text = recipe.servings.toString()
            binding.recipePrice.text = recipe.price.toString()
            binding.recipeStepByStepGuide.text = recipe.stepByStepGuide
            binding.recipeIngredients.text = constructRecipeIngredients(recipe.recipeIngredients)
            binding.recipeDietTitle.text = recipe.diet?.title ?: ""
            binding.recipeRating.text = recipe.rating?.rating ?: ""
            binding.recipeNRates.text = recipe.rating?.nRates.toString()
        }

    }

    private fun setUpRecipeReviews(){
        Log.e("hh","hh")
        viewModel.getRecipeReviews(recipeId = recipeId, context = requireContext()){ recipeReviews->
            Log.e("dasd",recipeReviews.toString())
            binding.recipeReviewsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                recipeReviewsAdapter =
                    RecipeReviewsAdapter(viewLifecycleOwner, context)
                adapter = recipeReviewsAdapter
            }
            recipeReviewsAdapter.submitList(recipeReviews)
        }

    }
}