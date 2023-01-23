package com.fitness.app.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.fitness.app.model.*
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.repository.AthleteHomeRepository

class AthleteHomeViewModel(private val athleteHomeRepository: AthleteHomeRepository) : ViewModel() {
    fun getDiet(dietId: String,context: Context,callback:(Diet)->Unit){
        return athleteHomeRepository.getDiet(dietId,context){diet->
            callback(diet)
        }
    }

    fun getUserProfilePicture(userId: String,context: Context,callback:(Bitmap)->Unit){
        return athleteHomeRepository.getUserProfilePicture(userId,context){userProfilePicture->
            callback(userProfilePicture)
        }
    }

    fun getProgram(programId: String,context: Context,callback:(Program)->Unit){
        return athleteHomeRepository.getProgram(programId,context){program->
            callback(program)
        }
    }

    fun getAllDietFoodItems(dietId: String,context: Context,callback:(ArrayList<Food>)->Unit) {
        return athleteHomeRepository.getAllDietFoodItems(dietId,context){dietFoods->
            callback(dietFoods)
        }
    }

    fun getAllProgramWorkoutItems(programId:String,context: Context,callback:(ArrayList<Workout>)->Unit) {
        return athleteHomeRepository.getAllProgramWorkoutItems(programId,context){workouts->
            callback(workouts)
        }
    }

    fun getAllCheckoutEventsItems(discoverEventsRequest: DiscoverEventsRequest,context: Context,callback:(ArrayList<CheckoutEvent>)->Unit){
        return athleteHomeRepository.getAllCheckoutEventsItems(discoverEventsRequest,context){checkoutEvents->
            callback(checkoutEvents)
        }
    }

    fun getAllDietRecipeItems(dietId:String, context: Context, callback:(ArrayList<Recipe>)->Unit){
        return athleteHomeRepository.getAllRecipeItems(dietId,context){dietRecipes->
            callback(dietRecipes)
        }
    }

    fun getAllUserEventsItems(userId:String, context: Context, callback:(ArrayList<YourEvent>)->Unit){
        return athleteHomeRepository.getAllUserEventsItems(userId,context){userEvents->
            callback(userEvents)
        }
    }

    fun getAllDayProgramWorkoutItems(programId: String, context: Context, callback:(ArrayList<DayWorkout>)->Unit){
        return athleteHomeRepository.getAllDayProgramWorkoutItems(programId,context){ dayWorkouts->
            callback(dayWorkouts)
        }
    }

    fun getAllDiscoverProgramsItems(discoverProgramsRequest: DiscoverProgramsRequest,context: Context,callback:(ArrayList<DiscoverProgram>)->Unit) {
        return athleteHomeRepository.getAllDiscoverProgramsItems(discoverProgramsRequest,context){discoverPrograms->
            callback(discoverPrograms)
        }
    }

    fun getAllDiscoverEventsItems(discoverEventsRequest: DiscoverEventsRequest,context: Context,callback:(ArrayList<DiscoverEvent>)->Unit) {
        return athleteHomeRepository.getAllDiscoverEventsItems(discoverEventsRequest,context){discoverEvents->
            callback(discoverEvents)
        }
    }

    fun getAllDiscoverDietsItems(discoverDietsRequest: DiscoverDietsRequest,context: Context,callback:(ArrayList<DiscoverDiet>)->Unit) {
        return athleteHomeRepository.getAllDiscoverDietsItems(discoverDietsRequest,context){discoverDiets->
            callback(discoverDiets)
        }
    }

    fun getAllDietPlanItems(dietId: String, context: Context, callback:(ArrayList<DietPlan>)->Unit){
        return athleteHomeRepository.getAllDietPlanItems(dietId,context){dietPlans->
            callback(dietPlans)
        }
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