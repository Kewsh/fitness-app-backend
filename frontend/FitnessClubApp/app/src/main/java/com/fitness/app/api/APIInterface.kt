package com.fitness.app.api

import com.fitness.app.model.AthleteLogIn
import com.fitness.app.model.AthleteSignUp
import com.fitness.app.model.AthleteSignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("user/login")
    fun logInAthlete(@Body athleteLogIn: AthleteLogIn) : Call<AthleteSignUpResponse>

    @POST("user/signup")
    fun signUpAthlete(@Body athleteSignUp: AthleteSignUp) : Call<AthleteSignUpResponse>
}