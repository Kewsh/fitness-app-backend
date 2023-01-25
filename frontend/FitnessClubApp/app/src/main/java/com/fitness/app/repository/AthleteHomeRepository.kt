package com.fitness.app.repository

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.fitness.app.R
import com.fitness.app.api.service.*
import com.fitness.app.model.*
import com.fitness.app.model.api.Club
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.model.api.response.diet.Nutritionist

class AthleteHomeRepository() {

    fun getNutritionistDiets(
        nutritionistId: String,
        context: Context,
        callback: (ArrayList<DiscoverDiet>) -> Unit
    ) {
        val nutritionistDiets: ArrayList<DiscoverDiet> = ArrayList()
        val apiService = NutritionistService(context)
        apiService.getNutritionistDiets(nutritionistId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val dietService = DietService(context)
                    dietService.getDietCoverPicture(response.data[i].id.toString()) { dietPicture ->
                        if (dietPicture != null) {
                            nutritionistDiets.add(
                                DiscoverDiet(
                                    dietPicture,
                                    response.data[i].title,
                                    response.data[i].nutritionist.fullName
                                )
                            )
                        }
                        callback(nutritionistDiets)
                    }

                }
            }
        }
    }

    fun getNutritionistPicture(nutritionistId: String,context: Context,callback: (Bitmap) -> Unit){
        val nutritionistService = NutritionistService(context)
        nutritionistService.getNutritionistPicture(nutritionistId) { nutritionistPicture ->
            if (nutritionistPicture != null) {
                callback(nutritionistPicture)
            }
        }
    }

    fun getDietComments(
        dietId: String,
        context: Context,
        callback: (ArrayList<Comment>) -> Unit
    ) {
        val dietService = DietService(context)
        val dietComments: ArrayList<Comment> = ArrayList()
        dietService.getDietComments(dietId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val athleteService = AthleteService(context)
                    athleteService.getAthleteProfilePicture(response.data[i].userId.toString()) { athleteProfilePicture ->
                        if (athleteProfilePicture != null) {
                            dietComments.add(
                                Comment(
                                    athleteProfilePicture,
                                    response.data[i].id,
                                    response.data[i].text,
                                    response.data[i].rate,
                                    response.data[i].programId,
                                    response.data[i].eventId,
                                    response.data[i].dietId,
                                    response.data[i].recipeReviewId,
                                    response.data[i].userId,
                                    response.data[i].updatedAt,
                                    response.data[i].createdAt,
                                    response.data[i].user
                                )
                            )
                            callback(dietComments)
                        }
                    }
                }
            }
        }
    }

    fun getRecipeReviews(
        recipeId: String,
        context: Context,
        callback: (ArrayList<RecipeReview>) -> Unit
    ) {
        Log.e("h2","h2")
        val recipeService = RecipeService(context)
        val recipeReviews: ArrayList<RecipeReview> = ArrayList()
        recipeService.getRecipeReviews(recipeId) { response ->
            Log.e("resp",response.toString())
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val recipeReviewService = RecipeReviewService(context)
                    Log.e("id",response.data[i].comment.recipeReviewId.toString())
                    recipeReviewService.getRecipeReviewPicture(response.data[i].id.toString()) { recipeReviewPicture ->
                        if (recipeReviewPicture != null) {
                            recipeReviews.add(
                                RecipeReview(
                                    recipeReviewPicture,
                                    response.data[i].id,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].recipeId,
                                    response.data[i].comment,
                                )
                            )
                            Log.e("rec",recipeReviews.toString())
                            callback(recipeReviews)
                        }
                    }
                }
            }
        }
    }

    fun getEventComments(
        eventId: String,
        context: Context,
        callback: (ArrayList<Comment>) -> Unit
    ) {
        val eventService = EventService(context)
        val eventComments: ArrayList<Comment> = ArrayList()
        eventService.getEventComments(eventId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val athleteService = AthleteService(context)
                    athleteService.getAthleteProfilePicture(response.data[i].userId.toString()) { athleteProfilePicture ->
                        if (athleteProfilePicture != null) {
                            eventComments.add(
                                Comment(
                                    athleteProfilePicture,
                                    response.data[i].id,
                                    response.data[i].text,
                                    response.data[i].rate,
                                    response.data[i].programId,
                                    response.data[i].eventId,
                                    response.data[i].dietId,
                                    response.data[i].recipeReviewId,
                                    response.data[i].userId,
                                    response.data[i].updatedAt,
                                    response.data[i].createdAt,
                                    response.data[i].user
                                )
                            )
                            callback(eventComments)
                        }
                    }
                }
            }
        }
    }

    fun getProgramComments(
        programId: String,
        context: Context,
        callback: (ArrayList<Comment>) -> Unit
    ) {
        val programService = ProgramService(context)
        val programComments: ArrayList<Comment> = ArrayList()
        programService.getProgramComments(programId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val athleteService = AthleteService(context)
                    athleteService.getAthleteProfilePicture(response.data[i].userId.toString()) { athleteProfilePicture ->
                        if (athleteProfilePicture != null) {
                            programComments.add(
                                Comment(
                                    athleteProfilePicture,
                                    response.data[i].id,
                                    response.data[i].text,
                                    response.data[i].rate,
                                    response.data[i].programId,
                                    response.data[i].eventId,
                                    response.data[i].dietId,
                                    response.data[i].recipeReviewId,
                                    response.data[i].userId,
                                    response.data[i].updatedAt,
                                    response.data[i].createdAt,
                                    response.data[i].user
                                )
                            )
                            callback(programComments)
                        }
                    }
                }
            }
        }
    }

    fun getClubById(
        clubId: String,
        context: Context,
        callback: (Club) -> Unit
    ) {
        val clubService = ClubService(context)
        clubService.getClubById(clubId) { response ->
            if (response != null) {
                val clubService = ClubService(context)
                clubService.getClubCoverPicture(response.data.id.toString()) { clubPicture ->
                    if (clubPicture != null) {
                        callback(
                            Club(
                                clubPicture,
                                response.data.id,
                                response.data.name,
                                response.data.manager,
                                response.data.description,
                                response.data.since,
                                response.data.email,
                                response.data.phoneNumber,
                                response.data.website,
                                response.data.address,
                                response.data.updatedAt,
                                response.data.createdAt,
                                response.data.nAthletes,
                                response.data.rating
                            )
                        )
                    }

                }

            }
        }
    }

    fun getClubPrograms(
        clubId: String,
        context: Context,
        callback: (ArrayList<DiscoverProgram>) -> Unit
    ) {
        val clubService = ClubService(context)
        val clubPrograms: ArrayList<DiscoverProgram> = ArrayList()
        clubService.getClubPrograms(clubId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val programService = ProgramService(context)
                    programService.getProgramCoverPicture(response.data[i].id.toString()) { programPicture ->
                        if (programPicture != null) {
                            clubPrograms.add(
                                DiscoverProgram(
                                    programPicture,
                                    response.data[i].title,
                                    response.data[i].club.name
                                )
                            )
                        }
                        callback(clubPrograms)
                    }
                }
            }
        }
    }

    fun getClubEvents(
        clubId: String,
        context: Context,
        callback: (ArrayList<Event>) -> Unit
    ) {
        val clubService = ClubService(context)
        val clubEvents: ArrayList<Event> = ArrayList()
        clubService.getClubEvents(clubId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val eventService = EventService(context)
                    eventService.getEventCoverPicture(response.data[i].id.toString()) { eventPicture ->
                        if (eventPicture != null) {
                            clubEvents.add(
                                Event(
                                    eventPicture,
                                    response.data[i].id,
                                    response.data[i].title,
                                    response.data[i].description,
                                    response.data[i].price,
                                    response.data[i].maxAttendees,
                                    response.data[i].startDate,
                                    response.data[i].endDate,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].clubId,
                                    response.data[i].club,
                                    response.data[i].nAttendees,
                                    response.data[i].rating
                                )
                            )
                        }
                        callback(clubEvents)
                    }
                }
            }
        }
    }

    fun getAllProgramWorkoutItems(
        programId: String,
        context: Context,
        callback: (ArrayList<Workout>) -> Unit
    ) {
        val apiService = ProgramService(context)
        val workouts: ArrayList<Workout> = ArrayList()
        apiService.getProgramWorkouts(programId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val workoutService = WorkoutService(context)
                    workoutService.getWorkoutCoverPicture(response.data[i].id.toString()) { workoutPicture ->
                        if (workoutPicture != null) {
                            workouts.add(
                                Workout(
                                    workoutPicture,
                                    response.data[i].id,
                                    response.data[i].setsAndReps,
                                    response.data[i].title,
                                    response.data[i].description,
                                    response.data[i].sets,
                                    response.data[i].reps,
                                    response.data[i].setTimeInSeconds,
                                    response.data[i].burntCalories,
                                    response.data[i].day,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].programId,
                                    response.data[i].program
                                )
                            )
                        }
                        callback(workouts)
                    }
                }
            }
        }

    }

    fun getAllCheckoutEventsItems(
        discoverEventsRequest: DiscoverEventsRequest,
        context: Context,
        callback: (ArrayList<Event>) -> Unit
    ) {
        val apiService = EventService(context)
        val checkoutEvents: ArrayList<Event> = ArrayList()
        apiService.discoverEvents(discoverEventsRequest) { response ->
            if (response != null) {
                Log.e("listSize", response.data.size.toString())
                for (i in 0 until response.data.size) {
                    apiService.getEventCoverPicture(response.data[i].id.toString()) { eventPicture ->
                        if (eventPicture != null) {
                            checkoutEvents.add(
                                Event(
                                    eventPicture,
                                    response.data[i].id,
                                    response.data[i].title,
                                    response.data[i].description,
                                    response.data[i].price,
                                    response.data[i].maxAttendees,
                                    response.data[i].startDate,
                                    response.data[i].endDate,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].clubId,
                                    response.data[i].club,
                                    response.data[i].nAttendees,
                                    response.data[i].rating
                                )
                            )
                        }
                        callback(checkoutEvents)
                    }

                }
            }
        }
    }

    fun getAllRecipeItems(dietId: String, context: Context, callback: (ArrayList<Recipe>) -> Unit) {
        val apiService = DietService(context)
        val dietRecipes: ArrayList<Recipe> = ArrayList()
        apiService.getDietRecipes(dietId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val recipeService = RecipeService(context)
                    recipeService.getRecipeCoverPicture(response.data[i].id.toString()) { recipePicture ->
                        if (recipePicture != null) {
                            dietRecipes.add(
                                Recipe(
                                    recipePicture,
                                    response.data[i].id,
                                    response.data[i].title,
                                    response.data[i].description,
                                    response.data[i].origin,
                                    response.data[i].prepTimeInMinutes,
                                    response.data[i].servings,
                                    response.data[i].price,
                                    response.data[i].stepByStepGuide,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].dietId,
                                    response.data[i].recipeIngredients,
                                    response.data[i].diet,
                                    response.data[i].rating)
                            )
                        }
                        callback(dietRecipes)
                    }
                }
            }
        }
    }

    fun getAllUserEventsItems(
        userId: String,
        context: Context,
        callback: (ArrayList<Event>) -> Unit
    ) {
        val apiService = AthleteService(context)
        val userEvents: ArrayList<Event> = ArrayList()
        apiService.getAthleteEvents(userId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val eventService = EventService(context)
                    eventService.getEventCoverPicture(response.data[i].id.toString()) { eventPicture ->
                        if (eventPicture != null) {
                            userEvents.add(
                                Event(
                                    eventPicture,
                                    response.data[i].id,
                                    response.data[i].title,
                                    response.data[i].description,
                                    response.data[i].price,
                                    response.data[i].maxAttendees,
                                    response.data[i].startDate,
                                    response.data[i].endDate,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].clubId,
                                    response.data[i].club,
                                    response.data[i].nAttendees,
                                    response.data[i].rating
                                )
                            )
                        }
                        callback(userEvents)
                    }
                }
            }
        }
    }

    fun getAllDietFoodItems(dietId: String, context: Context, callback: (ArrayList<Food>) -> Unit) {
        val apiService = DietService(context)
        val dietFoods: ArrayList<Food> = ArrayList()
        apiService.getDietFoods(dietId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    val foodService = FoodService(context)
                    foodService.getFoodCoverPicture(response.data[i].id.toString()) { foodPicture ->
                        if (foodPicture != null) {
                            dietFoods.add(
                                Food(
                                    foodPicture,
                                    response.data[i].amountAndTitle,
                                    response.data[i].id,
                                    response.data[i].amount,
                                    response.data[i].title,
                                    response.data[i].day
                                )
                            )
                        }
                        callback(dietFoods)
                    }
                }
            }
        }
    }

    fun getUserProfilePicture(userId: String, context: Context, callback: (Bitmap) -> Unit) {
        val apiService = AthleteService(context)
        apiService.getAthleteProfilePicture(userId) { profilePicture ->
            if (profilePicture != null) {
                callback(profilePicture)
            }
        }
    }

    fun getDiet(dietId: String, context: Context, callback: (Diet) -> Unit) {
        val apiService = DietService(context)
        apiService.getDietById(dietId) { response ->
            if (response != null) {
                val dietService = DietService(context)
                dietService.getDietCoverPicture(response.data.id.toString()) { dietPicture ->
                    if (dietPicture != null) {
                        callback(
                            Diet(
                                dietPicture,
                                response.data.id,
                                response.data.title,
                                response.data.description,
                                response.data.price,
                                response.data.createdAt,
                                response.data.updatedAt,
                                response.data.nutritionistId,
                                response.data.duration,
                                response.data.nAthletes,
                                response.data.rating,
                                response.data.nutritionist
                            )
                        )
                    }
                }
            }
        }
    }

    fun getRecipe(recipeId: String, context: Context, callback: (Recipe) -> Unit) {
        val apiService = RecipeService(context)
        apiService.getRecipeById(recipeId) { response ->
            if (response != null) {
                apiService.getRecipeCoverPicture(response.data.id.toString()) { recipePicture ->
                    if (recipePicture != null) {
                        callback(
                            Recipe(
                                recipePicture,
                                response.data.id,
                                response.data.title,
                                response.data.description,
                                response.data.origin,
                                response.data.prepTimeInMinutes,
                                response.data.servings,
                                response.data.price,
                                response.data.stepByStepGuide,
                                response.data.createdAt,
                                response.data.updatedAt,
                                response.data.dietId,
                                response.data.recipeIngredients,
                                response.data.diet,
                                response.data.rating
                            )
                        )
                    }
                }
            }
        }
    }

    fun getProgram(programId: String, context: Context, callback: (Program) -> Unit) {
        val apiService = ProgramService(context)
        apiService.getProgramById(programId) { response ->
            if (response != null) {
                val programService = ProgramService(context)
                programService.getProgramCoverPicture(response.data.id.toString()) { programPicture ->
                    if (programPicture != null) {
                        callback(
                            Program(
                                programPicture,
                                response.data.id,
                                response.data.title,
                                response.data.description,
                                response.data.coachName,
                                response.data.price,
                                response.data.createdAt,
                                response.data.updatedAt,
                                response.data.clubId,
                                response.data.duration,
                                response.data.nAthletes,
                                response.data.rating,
                                response.data.club,
                            )
                        )
                    }
                }
            }
        }
    }

    fun getWorkout(workoutId: String, context: Context, callback: (Workout) -> Unit) {
        val apiService = WorkoutService(context)
        apiService.getWorkoutById(workoutId) { response ->
            if (response != null) {
                val workoutService = WorkoutService(context)
                workoutService.getWorkoutCoverPicture(response.data.id.toString()) { workoutPicture ->
                    if (workoutPicture != null) {
                        callback(
                            Workout(
                                workoutPicture,
                                response.data.id,
                                response.data.setsAndReps,
                                response.data.title,
                                response.data.description,
                                response.data.sets,
                                response.data.reps,
                                response.data.setTimeInSeconds,
                                response.data.burntCalories,
                                response.data.day,
                                response.data.createdAt,
                                response.data.updatedAt,
                                response.data.programId,
                                response.data.program
                            )
                        )
                    }
                }
            }
        }
    }


    fun getEvent(eventId: String, context: Context, callback: (Event) -> Unit) {
        val apiService = EventService(context)
        apiService.getEventById(eventId) { response ->
            if (response != null) {
                val eventService = EventService(context)
                eventService.getEventCoverPicture(response.data.id.toString()) { eventPicture ->
                    if (eventPicture != null) {
                        callback(
                            Event(
                                eventPicture,
                                response.data.id,
                                response.data.title,
                                response.data.description,
                                response.data.price,
                                response.data.maxAttendees,
                                response.data.startDate,
                                response.data.endDate,
                                response.data.createdAt,
                                response.data.updatedAt,
                                response.data.clubId,
                                response.data.club,
                                response.data.nAttendees,
                                response.data.rating
                            )
                        )
                    }
                }
            }
        }
    }

    fun getAllDayProgramWorkoutItems(
        programId: String,
        context: Context,
        callback: (ArrayList<DayWorkout>) -> Unit
    ) {
        val apiService = ProgramService(context)
        val dayWorkouts = ArrayList<DayWorkout>()
        apiService.getProgramWorkouts(programId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    if (!dayWorkouts.any { dayWorkout -> dayWorkout.title == "Day " + response.data[i].day.toString() }) {

                        dayWorkouts.add(
                            DayWorkout(
                                "Day " + response.data[i].day.toString(),
                                R.drawable.ic_checked,
                                response.data.filter { workout ->
                                    workout.day == response.data[i].day
                                }.map { workout ->
                                    val workoutService = WorkoutService(context)
                                    workoutService.getWorkoutCoverPicture(workout.id.toString()) { workoutPicture ->
                                        if (workoutPicture != null) {
                                            workout.image = workoutPicture
                                        }
                                        callback(dayWorkouts)
                                    }
                                    Log.e("workout", workout.toString())
                                    workout
                                }
                            ))
                    }
                }
            }
        }
    }

    fun getAllDiscoverProgramsItems(
        discoverProgramsRequest: DiscoverProgramsRequest,
        context: Context,
        callback: (ArrayList<DiscoverProgram>) -> Unit
    ) {
        val discoverPrograms: ArrayList<DiscoverProgram> = ArrayList()
        val apiService = ProgramService(context)
        apiService.discoverPrograms(discoverProgramsRequest) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    apiService.getProgramCoverPicture(response.data[i].id.toString()) { programPicture ->
                        if (programPicture != null) {
                            discoverPrograms.add(
                                DiscoverProgram(
                                    programPicture,
                                    response.data[i].title,
                                    response.data[i].club.name
                                )
                            )
                        }
                        callback(discoverPrograms)
                    }

                }
            }
        }
    }

    fun getAllDiscoverEventsItems(
        discoverEventsRequest: DiscoverEventsRequest,
        context: Context,
        callback: (ArrayList<Event>) -> Unit
    ) {
        val apiService = EventService(context)
        val discoverEvents: ArrayList<Event> = ArrayList()
        apiService.discoverEvents(discoverEventsRequest) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    apiService.getEventCoverPicture(response.data[i].id.toString()) { eventPicture ->
                        if (eventPicture != null) {
                            discoverEvents.add(
                                Event(
                                    eventPicture,
                                    response.data[i].id,
                                    response.data[i].title,
                                    response.data[i].description,
                                    response.data[i].price,
                                    response.data[i].maxAttendees,
                                    response.data[i].startDate,
                                    response.data[i].endDate,
                                    response.data[i].createdAt,
                                    response.data[i].updatedAt,
                                    response.data[i].clubId,
                                    response.data[i].club,
                                    response.data[i].nAttendees,
                                    response.data[i].rating
                                )
                            )
                        }
                        callback(discoverEvents)
                    }

                }
            }
        }
    }

    fun getAllDiscoverDietsItems(
        discoverDietsRequest: DiscoverDietsRequest,
        context: Context,
        callback: (ArrayList<DiscoverDiet>) -> Unit
    ) {
        val discoverDiets: ArrayList<DiscoverDiet> = ArrayList()
        val apiService = DietService(context)
        apiService.discoverDiets(discoverDietsRequest) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    apiService.getDietCoverPicture(response.data[i].id.toString()) { dietPicture ->
                        if (dietPicture != null) {
                            discoverDiets.add(
                                DiscoverDiet(
                                    dietPicture,
                                    response.data[i].title,
                                    response.data[i].nutritionist.fullName
                                )
                            )
                        }
                        callback(discoverDiets)
                    }

                }
            }
        }
    }

    fun getAllDietPlanItems(
        dietId: String,
        context: Context,
        callback: (ArrayList<DietPlan>) -> Unit
    ) {
        val apiService = DietService(context)
        val dietPlans = ArrayList<DietPlan>()
        apiService.getDietFoods(dietId) { response ->
            if (response != null) {
                for (i in 0 until response.data.size) {
                    if (!dietPlans.any { dietPlan -> dietPlan.title == "Day " + response.data[i].day.toString() }) {
                        response.data
                        dietPlans.add(
                            DietPlan(
                                "Day " + response.data[i].day.toString(),
                                R.drawable.ic_checked,
                                response.data.filter { food ->
                                    food.day == response.data[i].day
                                }.map { food ->
                                    val foodService = FoodService(context)
                                    foodService.getFoodCoverPicture(food.id.toString()) { foodPicture ->
                                        if (foodPicture != null) {
                                            food.image = foodPicture
                                        }
                                        callback(dietPlans)
                                    }
                                    Log.e("food", food.toString())
                                    food
                                }
                            ))
                    }
                }
            }
        }
    }

    fun getAllUserCommentItems(): ArrayList<Comment> {
        val comments: ArrayList<Comment> = ArrayList()
//
//        var comment = Comment(
//            R.drawable.temp_mona_lisa_image,
//            "Mona Lisa",
//            "4.3",
//            "This program focuses on burning as much belly fat as\n" +
//                    "one could possibly take, in a span of 30 days."
//        )
//        comments.add(comment)
//
//        comment = Comment(
//            R.drawable.temp_mona_lisa_image,
//            "Mona Lisa",
//            "4.3",
//            "This program focuses on burning as much belly fat as\n" +
//                    "one could possibly take, in a span of 30 days."
//        )
//        comments.add(comment)
//
//        comment = Comment(
//            R.drawable.temp_mona_lisa_image,
//            "Mona Lisa",
//            "4.3",
//            "This program focuses on burning as much belly fat as\n" +
//                    "one could possibly take, in a span of 30 days."
//        )
//        comments.add(comment)
//
        return comments

    }

    fun getAllMoreDietItems(): ArrayList<MoreDiet> {
        val moreDiets: ArrayList<MoreDiet> = ArrayList()

        var moreDiet = MoreDiet(
            R.drawable.temp_diet_more_image,
            "Meat lover’s healthy diet",
            "by Dr. Ahmad Davoodi"
        )
        moreDiets.add(moreDiet)

        moreDiet = MoreDiet(
            R.drawable.temp_diet_more_image,
            "Meat lover’s healthy diet",
            "by Dr. Ahmad Davoodi"
        )
        moreDiets.add(moreDiet)

        moreDiet = MoreDiet(
            R.drawable.temp_diet_more_image,
            "Meat lover’s healthy diet",
            "by Dr. Ahmad Davoodi"
        )
        moreDiets.add(moreDiet)

        moreDiet = MoreDiet(
            R.drawable.temp_diet_more_image,
            "Meat lover’s healthy diet",
            "by Dr. Ahmad Davoodi"
        )
        moreDiets.add(moreDiet)

        return moreDiets

    }

    fun getAllWhoTriedFoodItems(): ArrayList<WhoTriedFood> {
        val whoTriedFoods: ArrayList<WhoTriedFood> = ArrayList()

        var whoTriedFood = WhoTriedFood(
            R.drawable.temp_who_tried_image,
            "Mona Lisa",
            "4.3",
            "This program focuses on burning as much\n" +
                    "belly fat as one could possibly take, in a\n" +
                    "span of 30 days  ..."
        )
        whoTriedFoods.add(whoTriedFood)

        whoTriedFood = WhoTriedFood(
            R.drawable.temp_who_tried_image,
            "Mona Lisa",
            "4.3",
            "This program focuses on burning as much\n" +
                    "belly fat as one could possibly take, in a\n" +
                    "span of 30 days  ..."
        )
        whoTriedFoods.add(whoTriedFood)

        return whoTriedFoods

    }
}