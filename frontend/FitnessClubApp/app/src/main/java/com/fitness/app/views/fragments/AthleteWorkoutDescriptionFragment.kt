package com.fitness.app.views.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fitness.app.R
import com.fitness.app.databinding.FragmentAthleteWorkoutDescriptionBinding

class AthleteWorkoutDescriptionFragment : Fragment(R.layout.fragment_athlete_workout_description) {
    lateinit var binding: FragmentAthleteWorkoutDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAthleteWorkoutDescriptionBinding.bind(view)

        binding.backButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager.popBackStack()
        }
    }

}