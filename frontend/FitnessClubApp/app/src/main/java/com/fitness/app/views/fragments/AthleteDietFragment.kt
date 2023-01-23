package com.fitness.app.views.fragments

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteDietBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class AthleteDietFragment : Fragment(R.layout.fragment_athlete_diet) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDietBinding
    lateinit var foodAdapter: FoodAdapter
    lateinit var recipeAdapter: RecipeAdapter
    lateinit var dietPlansAdapter: DietPlansAdapter
    var userId: Int = -1
    var dietId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteDietBinding.bind(view)
        val activity = activity as AthleteHomeActivity

        val intent: Intent = activity.intent
        userId = intent.getIntExtra("userId", -1)
        dietId = intent.getIntExtra("dietId", -1)

        setUpRecipes()
        setUpFoods()
        setUpDietPlans()
        setUpUserProfilePic()
        setUpCurrentProgram()

        binding.dietLayout.diet.setOnClickListener {
            val athleteDietDescriptionFragment = AthleteDietDescriptionFragment()
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.athleteHomeMainParentFragment, athleteDietDescriptionFragment)
            fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentManager.addToBackStack(null)
            fragmentManager.commit()
        }

    }

    private fun setUpUserProfilePic() {
        viewModel.getUserProfilePicture(userId = userId.toString(), context = requireContext()){profilePicture->
            binding.profilePic.setImageBitmap(profilePicture)
        }
    }

    private fun setUpCurrentProgram() {
        viewModel.getDiet(dietId = dietId.toString(), context = requireContext()){diet->
            binding.dietLayout.dietImage.background = BitmapDrawable(resources,diet.image)
            binding.dietLayout.dietTitle.text = diet.title
            binding.dietLayout.dietNutritionistFullName.text = diet.nutritionist.fullName
            binding.dietLayout.dietDescription.text = diet.description
        }
    }

    private fun setUpFoods() {
        viewModel.getAllDietFoodItems(
            dietId = dietId.toString(),
            context = requireContext()
        ) { foodRecipes ->
            binding.foodsRecyclerView.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                foodAdapter =
                    FoodAdapter(viewLifecycleOwner, context)
                adapter = foodAdapter
            }
            foodAdapter.submitList(foodRecipes)
        }
    }

    private fun setUpRecipes() {
        viewModel.getAllDietRecipeItems(
            dietId = dietId.toString(),
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

}