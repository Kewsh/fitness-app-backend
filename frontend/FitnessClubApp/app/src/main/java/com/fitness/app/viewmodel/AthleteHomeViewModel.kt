package com.fitness.app.viewmodel

import androidx.lifecycle.ViewModel
import com.fitness.app.R
import com.fitness.app.model.*
import com.fitness.app.repository.AthleteHomeRepository

class AthleteHomeViewModel(private val athleteHomeRepository: AthleteHomeRepository) : ViewModel() {
    fun getAllTodayDietItems(): ArrayList<Diet> {
        return athleteHomeRepository.getAllTodayDietItems()
    }

    fun getAllTodayWorkoutItems() : ArrayList<Workout> {
        return athleteHomeRepository.getAllTodayWorkoutItems()
    }

    fun getAllCheckoutEventsItems() : ArrayList<CheckoutEvent> {
        return athleteHomeRepository.getAllCheckoutEventsItems()
    }

    fun getAllFoodItems() : ArrayList<Food> {
        return athleteHomeRepository.getAllFoodItems()
    }

    fun getAllYourEventsItems() : ArrayList<YourEvent> {
        return athleteHomeRepository.getAllYourEventsItems()
    }

    fun getAllDayWorkoutItems(): ArrayList<DayWorkout> {
        return athleteHomeRepository.getAllDayWorkoutItems()
    }
}