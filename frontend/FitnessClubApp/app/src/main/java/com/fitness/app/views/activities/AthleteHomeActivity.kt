package com.fitness.app.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.fitness.app.R
import com.fitness.app.databinding.ActivityAthleteHomeBinding
import com.fitness.app.repository.AthleteHomeRepository
import com.fitness.app.viewmodel.AthleteHomeViewModel
import com.fitness.app.viewmodel.AthleteHomeViewModelFactory

class AthleteHomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAthleteHomeBinding
    lateinit var viewModel: AthleteHomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAthleteHomeBinding.inflate(layoutInflater)

        try {
            setContentView(binding.root)
            val athleteHomeRepository = AthleteHomeRepository()
            val athleteHomeViewModelFactory = AthleteHomeViewModelFactory(athleteHomeRepository)
            viewModel = ViewModelProvider(this, athleteHomeViewModelFactory)[AthleteHomeViewModel::class.java]

        } catch (exp: Exception) {
            Log.d("Exception", exp.toString())
        }
    }
}