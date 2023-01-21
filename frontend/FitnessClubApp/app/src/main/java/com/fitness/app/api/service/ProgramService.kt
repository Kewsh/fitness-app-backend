package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.ProgramEndpoints
import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.model.api.request.program.EnrollInProgramRequest
import com.fitness.app.model.api.response.program.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgramService(val context: Context) {
    fun enrollProgram(programId:String , enrollInProgramRequest: EnrollInProgramRequest, onResult: (EnrollInProgramResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ProgramEndpoints::class.java)
        retrofit.enrollProgram(programId , enrollInProgramRequest).enqueue(
            object : Callback<EnrollInProgramResponse> {
                override fun onFailure(call: Call<EnrollInProgramResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<EnrollInProgramResponse>, response: Response<EnrollInProgramResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun getProgramCoverPicture(programId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ProgramEndpoints::class.java)
        retrofit.getProgramCoverPicture(programId).enqueue(
            object :Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val res = response.body()
                    if(res!=null) {
                        val bmp = BitmapFactory.decodeStream(res.byteStream())
                        onResult(bmp)
                    }
                    else Log.e("ProgramCoverPic Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("ProgramCoverPic fail","ProgramCoverPic fail")
                    onResult(null)
                }

            }
        )
    }

    fun getProgramComments(programId: String, onResult: (GetProgramCommentsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ProgramEndpoints::class.java)
        retrofit.getProgramComments(programId).enqueue(
            object : Callback<GetProgramCommentsResponse> {
                override fun onFailure(call: Call<GetProgramCommentsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetProgramCommentsResponse>, response: Response<GetProgramCommentsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getProgramWorkouts(programId: String, onResult: (GetProgramWorkoutsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ProgramEndpoints::class.java)
        retrofit.getProgramWorkouts(programId).enqueue(
            object : Callback<GetProgramWorkoutsResponse> {
                override fun onFailure(call: Call<GetProgramWorkoutsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetProgramWorkoutsResponse>, response: Response<GetProgramWorkoutsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getProgramById(programId: String, onResult: (GetProgramByIdResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ProgramEndpoints::class.java)
        retrofit.getProgramById(programId).enqueue(
            object : Callback<GetProgramByIdResponse> {
                override fun onFailure(call: Call<GetProgramByIdResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetProgramByIdResponse>, response: Response<GetProgramByIdResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun discoverPrograms(discoverProgramsRequest: DiscoverProgramsRequest, onResult: (DiscoverProgramsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ProgramEndpoints::class.java)
        retrofit.discoverPrograms(discoverProgramsRequest).enqueue(
            object : Callback<DiscoverProgramsResponse> {
                override fun onFailure(call: Call<DiscoverProgramsResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<DiscoverProgramsResponse>, response: Response<DiscoverProgramsResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }
}