package com.fitness.app.api

import com.fitness.app.model.api.request.AthleteLogInRequest
import com.fitness.app.model.api.request.AthleteSignUpRequest
import com.fitness.app.model.api.request.ClubLogInRequest
import com.fitness.app.model.api.request.ClubSignUpRequest
import com.fitness.app.model.api.response.AthleteLogInResponse
import com.fitness.app.model.api.response.AthleteSignUpResponse
import com.fitness.app.model.api.response.ClubLogInResponse
import com.fitness.app.model.api.response.ClubSignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("user/login")
    fun logInAthlete(@Body athleteLogInRequest: AthleteLogInRequest) : Call<AthleteLogInResponse>

    @POST("user/signup")
    fun signUpAthlete(@Body athleteSignUpRequest: AthleteSignUpRequest) : Call<AthleteSignUpResponse>

    @POST("club/login")
    fun logInClub(@Body clubLogIn: ClubLogInRequest) : Call<ClubLogInResponse>

    @POST("club/signup")
    fun signUpClub(@Body clubSignUp: ClubSignUpRequest) : Call<ClubSignUpResponse>
}