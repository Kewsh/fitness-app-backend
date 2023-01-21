package com.fitness.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.fitness.app.R
import com.fitness.app.model.*
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.repository.AthleteHomeRepository

class AthleteHomeViewModel(private val athleteHomeRepository: AthleteHomeRepository) : ViewModel() {
    fun getAllTodayDietItems(): ArrayList<Diet> {
        return athleteHomeRepository.getAllTodayDietItems()
    }

    fun getAllTodayWorkoutItems() : ArrayList<Workout> {
        return athleteHomeRepository.getAllTodayWorkoutItems()
    }

    fun getAllCheckoutEventsItems(discoverEventsRequest: DiscoverEventsRequest,context: Context,callback:(ArrayList<CheckoutEvent>)->Unit){
        return athleteHomeRepository.getAllCheckoutEventsItems(discoverEventsRequest,context){checkoutEvents->
            callback(checkoutEvents)
        }
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

    fun getAllDiscoverProgramsItems(discoverProgramsRequest: DiscoverProgramsRequest,context: Context,callback:(ArrayList<DiscoverProgram>)->Unit) {
        return athleteHomeRepository.getAllDiscoverProgramsItems(discoverProgramsRequest,context){discoverPrograms->
            callback(discoverPrograms)
        }
    }

    fun getAllDietPlanItems(): ArrayList<DietPlan> {
        return athleteHomeRepository.getAllDietPlanItems()
    }

    fun getAllUserCommentItems():ArrayList<UserComment>{
        return athleteHomeRepository.getAllUserCommentItems()
    }

    fun getAllMoreDietItems():ArrayList<MoreDiet>{
        return athleteHomeRepository.getAllMoreDietItems()
    }

    fun getAllWhoTriedFoodItems():ArrayList<WhoTriedFood>{
        return athleteHomeRepository.getAllWhoTriedFoodItems()
    }
}