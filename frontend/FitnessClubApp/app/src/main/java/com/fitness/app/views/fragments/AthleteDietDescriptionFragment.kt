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
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteDietDescriptionBinding
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.response.diet.Nutritionist
import com.fitness.app.viewmodel.AthleteHomeViewModel
import java.util.concurrent.TimeUnit

class AthleteDietDescriptionFragment : Fragment(R.layout.fragment_athlete_diet_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDietDescriptionBinding
    lateinit var dietPlansAdapter: DietPlansAdapter
    lateinit var recipeAdapter: RecipeAdapter
    lateinit var commentAdapter: UserCommentAdapter
    lateinit var moreDietAdapter: DiscoverDietsAdapter

    var userId: Int = -1
    var dietId: String = ""
    var nutritionistId:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteDietDescriptionBinding.bind(view)

        val bundle = this.arguments
        if (bundle != null) {
            userId = bundle.getInt("userId")
            dietId = bundle.getString("dietId").toString()
        }

        Log.e("userId",userId.toString())
        Log.e("dietId",dietId)

        setUpDietData()
        setUpRecipes()
        setUpDietPlans()
        setUpDietComments()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }
    }

    private fun setUpDietData(){
        viewModel.getDiet(dietId = dietId, context = requireContext()){diet->
            binding.dietCoverPicture.background = BitmapDrawable(resources,diet.image)
            binding.dietTitle.text = diet.title
            binding.dietNutritionistName.text = diet.nutritionist.fullName
            binding.dietDescription.text = diet.description
            binding.dietDuration.text = diet.duration.toString()
            binding.dietNAthletes.text = diet.nAthletes.toString()
            binding.dietRating.text = diet.rating?.rating ?: ""
            binding.dietNRates.text = diet.rating?.nRates.toString()
            binding.dietPrice.text = diet.price.toString()
            nutritionistId = diet.nutritionist.id.toString()

            setNutritionistPicture(nutritionistId)
            setUpMoreDiets(nutritionistId)

            binding.dietNutritionistAboutName.text = diet.nutritionist.fullName
            binding.nutritionistNAthletes.text = diet.nutritionist.nAthletes.toString()
            binding.nutritionistSince.text = diet.nutritionist.since
            binding.nutritionistRating.text = diet.nutritionist.rating.rating
            binding.nutritionistNRates.text = diet.nutritionist.rating.nRates.toString()
            binding.nutritionistDescription.text = diet.nutritionist.description
            binding.nutritionistMoreByName.text = diet.nutritionist.fullName
        }

    }

    private fun setNutritionistPicture(nutritionistId: String){
        viewModel.getNutritionistPicture(nutritionistId = nutritionistId, context = requireContext()){nutritionistPicture->
            binding.nutritionistProfilePicture.setImageBitmap(nutritionistPicture)
        }
    }

    private fun setUpDietComments(){
        viewModel.getDietComments(dietId = dietId.toString(), context = requireContext()){dietComments->
            binding.dietCommentsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                commentAdapter =
                    UserCommentAdapter(viewLifecycleOwner, context)
                adapter = commentAdapter

            }
            commentAdapter.submitList(dietComments)
        }

    }

    private fun setUpMoreDiets(nutritionistId: String){
        viewModel.getNutritionistDiets(nutritionistId = nutritionistId ,requireContext()){nutritionistDiets->
            binding.moreDietsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                moreDietAdapter =
                    DiscoverDietsAdapter(viewLifecycleOwner, context,userId)
                adapter = moreDietAdapter

            }
            moreDietAdapter.submitList(nutritionistDiets)
        }
    }

    private fun setUpDietPlans(){
        viewModel.getAllDietPlanItems(dietId = dietId.toString(), context = requireContext()){ dietPlans->
            binding.dietPlansRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
                dietPlansAdapter =
                    DietPlansAdapter(viewLifecycleOwner, context)
                adapter = dietPlansAdapter
            }
            dietPlansAdapter.submitList(dietPlans)
        }
    }

    private fun setUpRecipes() {
        viewModel.getAllDietRecipeItems(
            dietId = dietId,
            context = requireContext()
        ) { dietRecipes ->
            binding.recipesRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                recipeAdapter = RecipeAdapter(viewLifecycleOwner, context)
                adapter = recipeAdapter

            }
            recipeAdapter.submitList(dietRecipes)
        }
    }

}