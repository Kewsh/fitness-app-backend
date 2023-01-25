package com.fitness.app.views.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fitness.app.R
import com.fitness.app.databinding.FragmentAthleteWorkoutDescriptionBinding
import com.fitness.app.viewmodel.AthleteHomeViewModel

class AthleteWorkoutDescriptionFragment : Fragment(R.layout.fragment_athlete_workout_description) {
    val viewModel: AthleteHomeViewModel by activityViewModels()
    lateinit var binding: FragmentAthleteWorkoutDescriptionBinding

    var workoutId:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteWorkoutDescriptionBinding.bind(view)

        val bundle = this.arguments
        if (bundle != null) {
            workoutId = bundle.get("workoutId").toString()
        }

        Log.e("workoutId",workoutId)

        setUpWorkoutData()

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }
    }

    private fun setUpWorkoutData() {
        viewModel.getWorkout(workoutId = workoutId, context = requireContext()){workout->
            binding.workoutCoverPicture.background = BitmapDrawable(resources,workout.image)
            binding.workoutTitle.text = workout.title
            binding.workoutProgramTitle.text = workout.program?.title ?: ""
            binding.workoutDescription.text = workout.description
            binding.workoutSets.text = workout.sets.toString()
            binding.workoutBurntCalories.text = workout.burntCalories.toString()
            binding.workoutReps.text = workout.reps.toString()
            binding.workoutSetTimeInSeconds.text = workout.setTimeInSeconds.toString()
        }
    }

}