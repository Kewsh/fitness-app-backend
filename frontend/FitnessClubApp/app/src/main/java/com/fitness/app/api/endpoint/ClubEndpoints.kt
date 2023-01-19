package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.club.ClubLogInRequest
import com.fitness.app.model.api.request.club.ClubSignUpRequest
import com.fitness.app.model.api.response.club.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClubEndpoints {
    @POST("club/login")
    fun logInClub(@Body clubLogIn: ClubLogInRequest) : Call<ClubLogInResponse>

    @POST("club/signup")
    fun signUpClub(@Body clubSignUp: ClubSignUpRequest) : Call<ClubSignUpResponse>

    @GET("club/{club_id}")
    fun getClubById(@Path(value = "club_id", encoded = true) clubId:String) : Call<GetClubByIdResponse>

    @GET("club/{club_id}/programs")
    fun getClubPrograms(@Path(value = "club_id", encoded = true) clubId:String) : Call<GetClubProgramsResponse>

    @GET("club/{club_id}/events")
    fun getClubEvents(@Path(value = "club_id", encoded = true) clubId:String) : Call<GetClubEventsResponse>

//    @GET("club/{club_id}/cover")
//    fun getClubCoverPicture(@Path(value = "club_id", encoded = true) clubId:String) : Call<GetClubCoverPictureResponse>

//    @GET("club/{club_id}/logo")
//    fun getClubLogo(@Path(value = "club_id", encoded = true) clubId:String) : Call<GetClubLogoResponse>

}