package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.athlete.AthleteSignUpRequest
import com.fitness.app.model.api.response.athlete.AthleteSignUpResponse
import com.fitness.app.model.api.response.athlete.GetAthleteEventsResponse
import com.fitness.app.model.api.response.workout.GetWorkoutByIdResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkoutEndpoints {
    @GET("workout/{workout_id}")
    fun getWorkoutById(@Path(value = "workout_id", encoded = true) workoutId:String) : Call<GetWorkoutByIdResponse>

//    @GET("workout/{workout_id}/cover")
//    fun getWorkoutCoverPicture(@Path(value = "workout_id", encoded = true) workoutId:String) : Call<GetWorkoutCoverPictureResponse>
}