package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.NutritionistEndpoints
import com.fitness.app.model.api.response.ErrorResponse
import com.fitness.app.model.api.response.nutritionist.GetNutritionistDietsResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NutritionistService(val context: Context) {
    fun getNutritionistPicture(nutritionistId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(NutritionistEndpoints::class.java)
        retrofit.getNutritionistPicture(nutritionistId).enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val res = response.body()
                    if(res!=null) {
                        val bmp = BitmapFactory.decodeStream(res.byteStream())
                        onResult(bmp)
                    }
                    else Log.e("NutritionistPic Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("NutritionistPic fail","NutritionistPic fail")
                    onResult(null)
                }

            }
        )
    }

    fun getNutritionistDiets(nutritionistId: String, onResult: (GetNutritionistDietsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(NutritionistEndpoints::class.java)
        retrofit.getNutritionistDiets(nutritionistId).enqueue(
            object : Callback<GetNutritionistDietsResponse> {
                override fun onFailure(call: Call<GetNutritionistDietsResponse>, t: Throwable) {
                    Log.e("t",t.message.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<GetNutritionistDietsResponse>, response: Response<GetNutritionistDietsResponse>) {
                    Log.e("resp",response.body().toString())
                    onResult(response.body())
                }
            }
        )
    }

}