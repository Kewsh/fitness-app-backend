package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.WorkoutEndpoints
import com.fitness.app.model.api.response.workout.GetWorkoutByIdResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutService(val context: Context) {
    fun getWorkoutCoverPicture(workoutId: String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(WorkoutEndpoints::class.java)
        retrofit.getWorkoutCoverPicture(workoutId).enqueue(
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
                    else Log.e("WorkoutCoverPic Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("WorkoutCoverPic fail","WorkoutCoverPic fail")
                    onResult(null)
                }

            }
        )
    }

    fun getWorkoutById(workoutId: String, onResult: (GetWorkoutByIdResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(WorkoutEndpoints::class.java)
        retrofit.getWorkoutById(workoutId).enqueue(
            object : Callback<GetWorkoutByIdResponse> {
                override fun onFailure(call: Call<GetWorkoutByIdResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetWorkoutByIdResponse>, response: Response<GetWorkoutByIdResponse>) {
                    onResult(response.body())
                }
            }
        )
    }
}