package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.DietEndpoints
import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.diet.PickDietRequest
import com.fitness.app.model.api.response.diet.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DietService (val context: Context) {
    fun pickDiet(pickDietRequest: PickDietRequest, onResult: (PickDietResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(DietEndpoints::class.java)
        retrofit.pickDiet(pickDietRequest).enqueue(
            object : Callback<PickDietResponse> {
                override fun onFailure(call: Call<PickDietResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<PickDietResponse>, response: Response<PickDietResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun getDietCoverPicture(dietId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(DietEndpoints::class.java)
        retrofit.getDietCoverPicture(dietId).enqueue(
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
                    else Log.e("DietCoverPicture Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("DietCoverPicture fail","DietCoverPicture fail")
                    onResult(null)
                }

            }
        )
    }

    fun getDietComments(dietId: String, onResult: (GetDietsCommentsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(DietEndpoints::class.java)
        retrofit.getDietComments(dietId).enqueue(
            object : Callback<GetDietsCommentsResponse> {
                override fun onFailure(call: Call<GetDietsCommentsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetDietsCommentsResponse>, response: Response<GetDietsCommentsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getDietFoods(dietId: String, onResult: (GetDietsFoodsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(DietEndpoints::class.java)
        retrofit.getDietFoods(dietId).enqueue(
            object : Callback<GetDietsFoodsResponse> {
                override fun onFailure(call: Call<GetDietsFoodsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetDietsFoodsResponse>, response: Response<GetDietsFoodsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getDietById(dietId: String, onResult: (GetDietByIdResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(DietEndpoints::class.java)
        retrofit.getDietById(dietId).enqueue(
            object : Callback<GetDietByIdResponse> {
                override fun onFailure(call: Call<GetDietByIdResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetDietByIdResponse>, response: Response<GetDietByIdResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun discoverDiets(discoverDietsRequest: DiscoverDietsRequest, onResult: (DiscoverDietsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(DietEndpoints::class.java)
        retrofit.discoverDiets(discoverDietsRequest).enqueue(
            object : Callback<DiscoverDietsResponse> {
                override fun onFailure(call: Call<DiscoverDietsResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<DiscoverDietsResponse>, response: Response<DiscoverDietsResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }
}