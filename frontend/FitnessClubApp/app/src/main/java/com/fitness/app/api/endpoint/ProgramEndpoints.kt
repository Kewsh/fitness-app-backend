package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.model.api.request.program.EnrollInProgramRequest
import com.fitness.app.model.api.response.program.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProgramEndpoints {
    @POST("program/discover")
    fun discoverPrograms(@Body discoverProgramsRequest: DiscoverProgramsRequest) : Call<DiscoverProgramsResponse>

    @GET("program/{program_id}")
    fun getProgramById(@Path(value = "program_id", encoded = true) programId:String) : Call<GetProgramByIdResponse>

    @GET("program/{program_id}/workouts")
    fun getProgramWorkouts(@Path(value = "program_id", encoded = true) programId:String) : Call<GetProgramWorkoutsResponse>

    @GET("program/{program_id}/comments")
    fun getProgramComments(@Path(value = "program_id", encoded = true) programId:String) : Call<GetProgramCommentsResponse>

    @GET("program/{program_id}/cover")
    fun getProgramCoverPicture(@Path(value = "program_id", encoded = true) programId:String) : Call<ResponseBody>

    @POST("program/{program_id}/enroll")
    fun enrollProgram(@Path(value = "program_id", encoded = true) programId:String , @Body enrollInProgramRequest: EnrollInProgramRequest) : Call<EnrollInProgramResponse>
}