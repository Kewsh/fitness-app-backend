package com.fitness.app.repository

import android.content.Context
import android.util.Log
import com.fitness.app.R
import com.fitness.app.api.service.*
import com.fitness.app.model.*
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest

class AthleteHomeRepository() {

    fun getAllProgramWorkoutItems(programId:String,context: Context,callback:(ArrayList<Workout>)->Unit) {
        val apiService = ProgramService(context)
        val workouts:ArrayList<Workout> = ArrayList()
        apiService.getProgramWorkouts(programId){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    val workoutService = WorkoutService(context)
                    workoutService.getWorkoutCoverPicture(response.data[i].id.toString()){workoutPicture->
                        if (workoutPicture != null) {
                            workouts.add(Workout(workoutPicture,response.data[i].setsAndReps))
                        }
                        callback(workouts)
                    }
                }
            }
        }

    }

    fun getAllCheckoutEventsItems(discoverEventsRequest: DiscoverEventsRequest,context: Context,callback:(ArrayList<CheckoutEvent>)->Unit) {
        val apiService = EventService(context)
        val checkoutEvents:ArrayList<CheckoutEvent> = ArrayList()
        apiService.discoverEvents(discoverEventsRequest){response->
            if(response!=null) {
                Log.e("listSize",response.data.size.toString())
                for (i in 0 until response.data.size){
                    apiService.getEventCoverPicture(response.data[i].id.toString()){eventPicture->
                        if (eventPicture != null) {
                            checkoutEvents.add(CheckoutEvent(eventPicture,response.data[i].title,response.data[i].club.name))
                        }
                        callback(checkoutEvents)
                    }

                }
            }
        }
    }

    fun getAllRecipeItems(dietId:String, context: Context, callback:(ArrayList<Recipe>)->Unit){
        val apiService = DietService(context)
        val dietRecipes:ArrayList<Recipe> = ArrayList()
        apiService.getDietRecipes(dietId){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    val recipeService = RecipeService(context)
                    recipeService.getRecipeCoverPicture(response.data[i].id.toString()){recipePicture->
                        if (recipePicture != null) {
                            dietRecipes.add(Recipe(recipePicture,response.data[i].title))
                        }
                        callback(dietRecipes)
                    }
                }
            }
        }
    }

    fun getAllUserEventsItems(userId:String, context: Context, callback:(ArrayList<YourEvent>)->Unit){
        val apiService = AthleteService(context)
        val userEvents:ArrayList<YourEvent> = ArrayList()
        apiService.getAthleteEvents(userId){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    val eventService = EventService(context)
                    eventService.getEventCoverPicture(response.data[i].id.toString()){eventPicture->
                        if (eventPicture != null) {
                            userEvents.add(YourEvent(eventPicture,response.data[i].title,response.data[i].club.name))
                        }
                        callback(userEvents)
                    }
                }
            }
        }
    }

    fun getAllDietFoodItems(dietId:String, context: Context, callback:(ArrayList<Food>)->Unit) {
        val apiService = DietService(context)
        val dietFoods:ArrayList<Food> = ArrayList()
        apiService.getDietFoods(dietId){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    val foodService = FoodService(context)
                    foodService.getFoodCoverPicture(response.data[i].id.toString()){foodPicture->
                        if (foodPicture != null) {
                            dietFoods.add(Food(foodPicture,response.data[i].amountAndTitle))
                        }
                        callback(dietFoods)
                    }
                }
            }
        }
    }

    fun getAllDayWorkoutItems(): ArrayList<DayWorkout> {
        val dayWorkouts:ArrayList<DayWorkout> = ArrayList()

//        var dayWorkout = DayWorkout("Day 1",R.drawable.ic_checked,getAllProgramWorkoutItems())
//        dayWorkouts.add(dayWorkout)
//
//        dayWorkout = DayWorkout("Day 2",R.drawable.ic_checked,getAllProgramWorkoutItems())
//        dayWorkouts.add(dayWorkout)
//
//        dayWorkout = DayWorkout("Day 3",R.drawable.ic_checked,getAllProgramWorkoutItems())
//        dayWorkouts.add(dayWorkout)
//
//        dayWorkout = DayWorkout("Day 4",R.drawable.ic_checked,getAllProgramWorkoutItems())
//        dayWorkouts.add(dayWorkout)
//
//        dayWorkout = DayWorkout("Day 5",R.drawable.ic_checked,getAllProgramWorkoutItems())
//        dayWorkouts.add(dayWorkout)

        return dayWorkouts

    }

    fun getAllDiscoverProgramsItems(discoverProgramsRequest: DiscoverProgramsRequest,context: Context,callback:(ArrayList<DiscoverProgram>)->Unit) {
        val discoverPrograms:ArrayList<DiscoverProgram> = ArrayList()
        val apiService = ProgramService(context)
        apiService.discoverPrograms(discoverProgramsRequest){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    apiService.getProgramCoverPicture(response.data[i].id.toString()){programPicture->
                        if (programPicture != null) {
                            discoverPrograms.add(DiscoverProgram(programPicture,response.data[i].title,response.data[i].club.name))
                        }
                        callback(discoverPrograms)
                    }

                }
            }
        }
    }

    fun getAllDiscoverEventsItems(discoverEventsRequest: DiscoverEventsRequest,context: Context,callback:(ArrayList<DiscoverEvent>)->Unit) {
        val apiService = EventService(context)
        val discoverEvents:ArrayList<DiscoverEvent> = ArrayList()
        apiService.discoverEvents(discoverEventsRequest){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    apiService.getEventCoverPicture(response.data[i].id.toString()){eventPicture->
                        if (eventPicture != null) {
                            discoverEvents.add(DiscoverEvent(eventPicture,response.data[i].title,response.data[i].club.name))
                        }
                        callback(discoverEvents)
                    }

                }
            }
        }
    }

    fun getAllDiscoverDietsItems(discoverDietsRequest: DiscoverDietsRequest,context: Context,callback:(ArrayList<DiscoverDiet>)->Unit) {
        val discoverDiets:ArrayList<DiscoverDiet> = ArrayList()
        val apiService = DietService(context)
        apiService.discoverDiets(discoverDietsRequest){response->
            if(response!=null) {
                for (i in 0 until response.data.size){
                    apiService.getDietCoverPicture(response.data[i].id.toString()){dietPicture->
                        if (dietPicture != null) {
                            discoverDiets.add(DiscoverDiet(dietPicture,response.data[i].title,response.data[i].nutritionist.fullName))
                        }
                        callback(discoverDiets)
                    }

                }
            }
        }
    }

    fun getAllDietPlanItems(): ArrayList<DietPlan> {
        val dietPlans:ArrayList<DietPlan> = ArrayList()

//        var dietPlan = DietPlan("Day 1",R.drawable.ic_checked,getAllDietFoodItems())
//        dietPlans.add(dietPlan)
//
//        dietPlan = DietPlan("Day 2",R.drawable.ic_checked,getAllDietFoodItems())
//        dietPlans.add(dietPlan)
//
//        dietPlan = DietPlan("Day 3",R.drawable.ic_checked,getAllDietFoodItems())
//        dietPlans.add(dietPlan)

        return dietPlans

    }

    fun getAllUserCommentItems(): ArrayList<UserComment> {
        val userComments:ArrayList<UserComment> = ArrayList()

        var userComment = UserComment(R.drawable.temp_mona_lisa_image,"Mona Lisa","4.3","This program focuses on burning as much belly fat as\n" +
                "one could possibly take, in a span of 30 days.")
        userComments.add(userComment)

        userComment = UserComment(R.drawable.temp_mona_lisa_image,"Mona Lisa","4.3","This program focuses on burning as much belly fat as\n" +
                "one could possibly take, in a span of 30 days.")
        userComments.add(userComment)

        userComment = UserComment(R.drawable.temp_mona_lisa_image,"Mona Lisa","4.3","This program focuses on burning as much belly fat as\n" +
                "one could possibly take, in a span of 30 days.")
        userComments.add(userComment)

        return userComments

    }

    fun getAllMoreDietItems(): ArrayList<MoreDiet> {
        val moreDiets:ArrayList<MoreDiet> = ArrayList()

        var moreDiet = MoreDiet(R.drawable.temp_diet_more_image,"Meat lover’s healthy diet","by Dr. Ahmad Davoodi")
        moreDiets.add(moreDiet)

        moreDiet = MoreDiet(R.drawable.temp_diet_more_image,"Meat lover’s healthy diet","by Dr. Ahmad Davoodi")
        moreDiets.add(moreDiet)

        moreDiet = MoreDiet(R.drawable.temp_diet_more_image,"Meat lover’s healthy diet","by Dr. Ahmad Davoodi")
        moreDiets.add(moreDiet)

        moreDiet = MoreDiet(R.drawable.temp_diet_more_image,"Meat lover’s healthy diet","by Dr. Ahmad Davoodi")
        moreDiets.add(moreDiet)

        return moreDiets

    }

    fun getAllWhoTriedFoodItems(): ArrayList<WhoTriedFood> {
        val whoTriedFoods:ArrayList<WhoTriedFood> = ArrayList()

        var whoTriedFood = WhoTriedFood(R.drawable.temp_who_tried_image,"Mona Lisa","4.3","This program focuses on burning as much\n" +
                "belly fat as one could possibly take, in a\n" +
                "span of 30 days  ...")
        whoTriedFoods.add(whoTriedFood)

        whoTriedFood = WhoTriedFood(R.drawable.temp_who_tried_image,"Mona Lisa","4.3","This program focuses on burning as much\n" +
                "belly fat as one could possibly take, in a\n" +
                "span of 30 days  ...")
        whoTriedFoods.add(whoTriedFood)

        return whoTriedFoods

    }
}