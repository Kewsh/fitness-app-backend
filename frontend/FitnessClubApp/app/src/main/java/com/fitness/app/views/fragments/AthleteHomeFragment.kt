package com.fitness.app.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.*
import com.fitness.app.databinding.FragmentAthleteHomeBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit


class AthleteHomeFragment : Fragment(R.layout.fragment_athlete_home) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteHomeBinding
    lateinit var workoutAdapter: WorkoutAdapter
    lateinit var dietAdapter: DietAdapter
    lateinit var foodAdapter: FoodAdapter
    lateinit var yourEventsAdapter: YourEventsAdapter
    lateinit var checkoutEventsAdapter: CheckoutEventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteHomeBinding.bind(view)
//        val navigator = Navigation.findNavController(view)
        val activity = activity as AthleteHomeActivity

        setUpWorkouts()
        setUpDiets()
        setUpFoods()
        setUpYourEvents()
        setUpCheckoutEvents()
        Picasso.get().load(R.drawable.athlete_temp_new_events_item_image).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(binding.profilePic)

    }

    private fun setUpWorkouts(){
        binding.workoutsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            workoutAdapter =
                WorkoutAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = workoutAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        workoutAdapter.submitList(viewModel.getAllTodayWorkoutItems())
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

    private fun setUpYourEvents(){
        binding.yourEventsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            yourEventsAdapter = YourEventsAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = yourEventsAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        yourEventsAdapter.submitList(viewModel.getAllYourEventsItems())
    }

    private fun setUpCheckoutEvents(){
        binding.checkoutEventsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            checkoutEventsAdapter = CheckoutEventsAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = checkoutEventsAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        checkoutEventsAdapter.submitList(viewModel.getAllCheckoutEventsItems())
    }
}