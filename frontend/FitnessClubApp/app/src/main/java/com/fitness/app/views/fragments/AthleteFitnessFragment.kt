package com.fitness.app.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fitness.app.R
import com.fitness.app.adapters.DayWorkoutAdapter
import com.fitness.app.adapters.WorkoutAdapter
import com.fitness.app.databinding.FragmentAthleteFitnessBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.views.activities.AthleteHomeActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class AthleteFitnessFragment : Fragment(R.layout.fragment_athlete_fitness) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteFitnessBinding
    lateinit var workoutAdapter: WorkoutAdapter
    lateinit var dayWorkoutAdapter: DayWorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAthleteFitnessBinding.bind(view)
        val activity = activity as AthleteHomeActivity
        setUpWorkouts()
        setUpDayWorkouts()
        Picasso.get().load(R.drawable.athlete_temp_new_events_item_image).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(
            NetworkPolicy.NO_CACHE).into(binding.profilePic)

        binding.programLayout.program.setOnClickListener {
            val programDescriptionFragment = AthleteProgramDescriptionFragment()
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.athleteHomeMainParentFragment, programDescriptionFragment)
            fragmentManager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentManager.addToBackStack(null)
            fragmentManager.commit()
        }

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

//        workoutAdapter.submitList(viewModel.getAllProgramWorkoutItems())
    }

    private fun setUpDayWorkouts(){
        binding.dayWorkoutsRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            dayWorkoutAdapter =
                DayWorkoutAdapter(viewLifecycleOwner, context)
//            dietAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            adapter = dayWorkoutAdapter
            postponeEnterTransition(300, TimeUnit.MILLISECONDS)
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

        }

        dayWorkoutAdapter.submitList(viewModel.getAllDayWorkoutItems())
    }
}