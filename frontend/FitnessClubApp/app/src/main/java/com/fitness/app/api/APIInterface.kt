package com.fitness.app.api

import com.fitness.app.model.api.request.athlete.AthleteLogInRequest
import com.fitness.app.model.api.request.athlete.AthleteSignUpRequest
import com.fitness.app.model.api.request.club.ClubLogInRequest
import com.fitness.app.model.api.request.club.ClubSignUpRequest
import com.fitness.app.model.api.response.athlete.AthleteLogInResponse
import com.fitness.app.model.api.response.athlete.AthleteSignUpResponse
import com.fitness.app.model.api.response.club.ClubLogInResponse
import com.fitness.app.model.api.response.club.ClubSignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {

    @POST("club/login")
    fun logInClub(@Body clubLogIn: ClubLogInRequest) : Call<ClubLogInResponse>

    @POST("club/signup")
    fun signUpClub(@Body clubSignUp: ClubSignUpRequest) : Call<ClubSignUpResponse>
}