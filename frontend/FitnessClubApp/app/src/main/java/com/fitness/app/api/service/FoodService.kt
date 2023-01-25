package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.FoodEndpoints
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodService (val context: Context) {
    fun getFoodCoverPicture(foodId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(FoodEndpoints::class.java)
        retrofit.getFoodCoverPicture(foodId).enqueue(
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
                    else Log.e("FoodCoverPicture Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("FoodCoverPicture fail","FoodCoverPicture fail")
                    onResult(null)
                }

            }
        )
    }
}