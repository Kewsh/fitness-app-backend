package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteDietDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import java.util.concurrent.TimeUnit

class AthleteDietDescriptionFragment : Fragment(R.layout.fragment_athlete_diet_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDietDescriptionBinding
    lateinit var dietPlansAdapter: DietPlansAdapter
    lateinit var recipeAdapter: RecipeAdapter
    lateinit var userCommentAdapter: UserCommentAdapter
    lateinit var moreDietAdapter: MoreDietAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteDietDescriptionBinding.bind(view)

        setUpFoods()
        setUpDietPlans()
        setUpUserComments()
        setUpMoreDiets()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }
    }

    private fun setUpMoreDiets(){
        binding.moreDietsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            moreDietAdapter =
                MoreDietAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = moreDietAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        moreDietAdapter.submitList(viewModel.getAllMoreDietItems())
    }

    private fun setUpUserComments(){
        binding.userCommentsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            userCommentAdapter =
                UserCommentAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = userCommentAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        userCommentAdapter.submitList(viewModel.getAllUserCommentItems())
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

}