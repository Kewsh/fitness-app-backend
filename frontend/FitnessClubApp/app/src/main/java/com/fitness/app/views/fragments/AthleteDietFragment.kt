package com.fitness.app.views.fragments

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteDietBinding.bind(view)
        val activity = activity as AthleteHomeActivity

        val intent: Intent = activity.intent
        userId = intent.getIntExtra("userId", -1)

        setUpDiets()
        setUpFoods()
        setUpDietPlans()
        setUpUserProfilePic()

        binding.dietLayout.food.setOnClickListener {
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

    private fun setUpFoods(){
//        binding.foodsRecyclerView.apply {
//            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
//            setHasFixedSize(true)
//            recipeAdapter = RecipeAdapter(viewLifecycleOwner, context)
////            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
//            adapter = recipeAdapter
//            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
//            viewTreeObserver.addOnPreDrawListener {
//                startPostponedEnterTransition()
//                true
//            }
//
//        }
//
//        recipeAdapter.submitList(viewModel.getAllDietRecipeItems())
    }

    private fun setUpDiets(){
//        binding.dietsRecyclerView.apply {
//            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
//            setHasFixedSize(true)
//            foodAdapter =
//                FoodAdapter(viewLifecycleOwner, context)
////            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
//            adapter = foodAdapter
//            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
//            viewTreeObserver.addOnPreDrawListener {
//                startPostponedEnterTransition()
//                true
//            }
//
//        }
//
//        foodAdapter.submitList(viewModel.getAllDietFoodItems())
    }

    private fun setUpDietPlans(){
        binding.dietPlansRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            dietPlansAdapter =
                DietPlansAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = dietPlansAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        dietPlansAdapter.submitList(viewModel.getAllDietPlanItems())
    }

}