package com.fitness.app.repository

import com.fitness.app.R
import com.fitness.app.model.*

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

    fun getAllCheckoutEventsItems(): ArrayList<CheckoutEvent> {
        val checkoutEvents:ArrayList<CheckoutEvent> = ArrayList()

        var checkoutEvent = CheckoutEvent(R.drawable.athlete_temp_new_events_item_image,"marathon","By Pulse")
        checkoutEvents.add(checkoutEvent)

        checkoutEvent = CheckoutEvent(R.drawable.athlete_temp_new_events_item_image,"marathon","By Pulse")
        checkoutEvents.add(checkoutEvent)

        checkoutEvent = CheckoutEvent(R.drawable.athlete_temp_new_events_item_image,"marathon","By Pulse")
        checkoutEvents.add(checkoutEvent)

        checkoutEvent = CheckoutEvent(R.drawable.athlete_temp_new_events_item_image,"marathon","By Pulse")
        checkoutEvents.add(checkoutEvent)

        checkoutEvent = CheckoutEvent(R.drawable.athlete_temp_new_events_item_image,"marathon","By Pulse")
        checkoutEvents.add(checkoutEvent)

        return checkoutEvents
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

    fun getAllDiscoverProgramsItems(): ArrayList<DiscoverProgram> {
        val discoverPrograms:ArrayList<DiscoverProgram> = ArrayList()

        var discoverProgram = DiscoverProgram(R.drawable.temp_program_image,"Lose belly fat in 30 days","by Pulse Fitness Club")
        discoverPrograms.add(discoverProgram)

        discoverProgram = DiscoverProgram(R.drawable.temp_program_image,"Lose belly fat in 30 days","by Pulse Fitness Club")
        discoverPrograms.add(discoverProgram)

        discoverProgram = DiscoverProgram(R.drawable.temp_program_image,"Lose belly fat in 30 days","by Pulse Fitness Club")
        discoverPrograms.add(discoverProgram)

        discoverProgram = DiscoverProgram(R.drawable.temp_program_image,"Lose belly fat in 30 days","by Pulse Fitness Club")
        discoverPrograms.add(discoverProgram)

        discoverProgram = DiscoverProgram(R.drawable.temp_program_image,"Lose belly fat in 30 days","by Pulse Fitness Club")
        discoverPrograms.add(discoverProgram)

        return discoverPrograms

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
}