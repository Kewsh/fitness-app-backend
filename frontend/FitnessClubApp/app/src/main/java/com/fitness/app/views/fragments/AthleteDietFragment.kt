package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteDietBinding
import com.fitness.app.databinding.FragmentAthleteFitnessBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class AthleteDietFragment : Fragment(R.layout.fragment_athlete_diet) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteDietBinding
    lateinit var dietAdapter: DietAdapter
    lateinit var foodAdapter: FoodAdapter
    lateinit var dietPlansAdapter: DietPlansAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteDietBinding.bind(view)
        val activity = activity as AthleteHomeActivity
        setUpDiets()
        setUpFoods()
        setUpDietPlans()
        Picasso.get().load(R.drawable.athlete_temp_new_events_item_image).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(
            NetworkPolicy.NO_CACHE).into(binding.profilePic)

    }

    private fun setUpFoods(){
        binding.foodsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            foodAdapter = FoodAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = foodAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        foodAdapter.submitList(viewModel.getAllFoodItems())
    }

    private fun setUpDiets(){
        binding.dietsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            dietAdapter =
                DietAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = dietAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        dietAdapter.submitList(viewModel.getAllTodayDietItems())
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