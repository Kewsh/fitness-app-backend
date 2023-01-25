package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.athlete.AthleteLogInRequest
import com.fitness.app.model.api.request.athlete.AthleteSignUpRequest
import com.fitness.app.model.api.response.athlete.AthleteLogInResponse
import com.fitness.app.model.api.response.athlete.AthleteSignUpResponse
import com.fitness.app.model.api.response.athlete.GetAthleteEventsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AthleteEndpoints {
    @POST("user/login")
    fun logInAthlete(@Body athleteLogInRequest: AthleteLogInRequest) : Call<AthleteLogInResponse>

    @POST("user/signup")
    fun signUpAthlete(@Body athleteSignUpRequest: AthleteSignUpRequest) : Call<AthleteSignUpResponse>

    @GET("user/{user_id}/events")
    fun getAthleteEvents(@Path(value = "user_id", encoded = true) userId:String) : Call<GetAthleteEventsResponse>

    @GET("user/{user_id}/profile-picture")
    fun getAthleteProfilePicture(@Path(value = "user_id", encoded = true) userId:String) : Call<ResponseBody>
}