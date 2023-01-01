package com.fitness.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fitness.app.repository.AthleteHomeRepository

class AthleteHomeViewModelFactory (private val athleteHomeRepository: AthleteHomeRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AthleteHomeViewModel(athleteHomeRepository) as T
    }
}