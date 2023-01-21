package com.fitness.app.repository

import android.content.Context
import android.util.Log
import com.fitness.app.R
import com.fitness.app.api.service.DietService
import com.fitness.app.api.service.EventService
import com.fitness.app.api.service.ProgramService
import com.fitness.app.model.*
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest

class AthleteHomeRepository() {

    fun getAllTodayWorkoutItems(): ArrayList<Workout> {
        val workouts:ArrayList<Workout> = ArrayList()

        var workout = Workout(R.drawable.athlete_temp_workout_item_image,"2x10 Arm")
        workouts.add(workout)

        workout = Workout(R.drawable.athlete_temp_workout_item_image,"2x8 chest")
        workouts.add(workout)

        workout = Workout(R.drawable.athlete_temp_workout_item_image,"1x5 dumbbell")
        workouts.add(workout)

        workout = Workout(R.drawable.athlete_temp_workout_item_image,"2x8 chest")
        workouts.add(workout)

        workout = Workout(R.drawable.athlete_temp_workout_item_image,"2x10 Arm")
        workouts.add(workout)

        return workouts

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

    fun getAllFoodItems(): ArrayList<Food> {
        val foods:ArrayList<Food> = ArrayList()

        var food = Food(R.drawable.athlete_temp_food_item_image,"Chicken")
        foods.add(food)

        food = Food(R.drawable.athlete_temp_food_item_image,"pasta")
        foods.add(food)

        food = Food(R.drawable.athlete_temp_food_item_image,"Masala")
        foods.add(food)

        food = Food(R.drawable.athlete_temp_food_item_image,"broccoli")
        foods.add(food)

        food = Food(R.drawable.athlete_temp_food_item_image,"pasta")
        foods.add(food)

        return foods
    }

    fun getAllYourEventsItems(): ArrayList<YourEvent> {
        val yourEvents:ArrayList<YourEvent> = ArrayList()

        var yourEvent = YourEvent(R.drawable.athlete_temp_your_event_item_image,"marathon","By Pulse")
        yourEvents.add(yourEvent)

        yourEvent = YourEvent(R.drawable.athlete_temp_your_event_item_image,"marathon","By Pulse")
        yourEvents.add(yourEvent)

        yourEvent = YourEvent(R.drawable.athlete_temp_your_event_item_image,"marathon","By Pulse")
        yourEvents.add(yourEvent)

        yourEvent = YourEvent(R.drawable.athlete_temp_your_event_item_image,"marathon","By Pulse")
        yourEvents.add(yourEvent)

        yourEvent = YourEvent(R.drawable.athlete_temp_your_event_item_image,"marathon","By Pulse")
        yourEvents.add(yourEvent)

        return yourEvents
    }

    fun getAllTodayDietItems(): ArrayList<Diet> {
        val diets:ArrayList<Diet> = ArrayList()

        var diet = Diet(R.drawable.athlete_temp_diet_item_image,"2x Chicken")
        diets.add(diet)

        diet = Diet(R.drawable.athlete_temp_diet_item_image,"5x Broccoli")
        diets.add(diet)

        diet = Diet(R.drawable.athlete_temp_diet_item_image,"200g rice")
        diets.add(diet)

        diet = Diet(R.drawable.athlete_temp_diet_item_image,"2x Chicken")
        diets.add(diet)

        diet = Diet(R.drawable.athlete_temp_diet_item_image,"200g rice")
        diets.add(diet)

        return diets
    }

    fun getAllDayWorkoutItems(): ArrayList<DayWorkout> {
        val dayWorkouts:ArrayList<DayWorkout> = ArrayList()

        var dayWorkout = DayWorkout("Day 1",R.drawable.ic_checked,getAllTodayWorkoutItems())
        dayWorkouts.add(dayWorkout)

        dayWorkout = DayWorkout("Day 2",R.drawable.ic_checked,getAllTodayWorkoutItems())
        dayWorkouts.add(dayWorkout)

        dayWorkout = DayWorkout("Day 3",R.drawable.ic_checked,getAllTodayWorkoutItems())
        dayWorkouts.add(dayWorkout)

        dayWorkout = DayWorkout("Day 4",R.drawable.ic_checked,getAllTodayWorkoutItems())
        dayWorkouts.add(dayWorkout)

        dayWorkout = DayWorkout("Day 5",R.drawable.ic_checked,getAllTodayWorkoutItems())
        dayWorkouts.add(dayWorkout)

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

        var dietPlan = DietPlan("Day 1",R.drawable.ic_checked,getAllTodayDietItems())
        dietPlans.add(dietPlan)

        dietPlan = DietPlan("Day 2",R.drawable.ic_checked,getAllTodayDietItems())
        dietPlans.add(dietPlan)

        dietPlan = DietPlan("Day 3",R.drawable.ic_checked,getAllTodayDietItems())
        dietPlans.add(dietPlan)

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