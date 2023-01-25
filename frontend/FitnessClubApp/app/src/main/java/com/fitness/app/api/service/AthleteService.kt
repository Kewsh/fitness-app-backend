package com.fitness.app.api.service

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.R
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.AthleteEndpoints
import com.fitness.app.model.api.request.athlete.AthleteLogInRequest
import com.fitness.app.model.api.request.athlete.AthleteSignUpRequest
import com.fitness.app.model.api.response.ErrorResponse
import com.fitness.app.model.api.response.athlete.AthleteLogInResponse
import com.fitness.app.model.api.response.athlete.AthleteSignUpResponse
import com.fitness.app.model.api.response.athlete.GetAthleteEventsResponse
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AthleteService(val context: Context) {
    fun getAthleteEvents(userId: String, onResult: (GetAthleteEventsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AthleteEndpoints::class.java)
        retrofit.getAthleteEvents(userId).enqueue(
            object : Callback<GetAthleteEventsResponse> {
                override fun onFailure(call: Call<GetAthleteEventsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetAthleteEventsResponse>, response: Response<GetAthleteEventsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getAthleteProfilePicture(userId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AthleteEndpoints::class.java)
        retrofit.getAthleteProfilePicture(userId).enqueue(
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
                    else Log.e("AthleteProfilePic Res","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("getAthleteProfile fail","getAthleteProfile fail")
                }

            }
        )
    }
    fun signUpAthlete(athleteSignUpRequest: AthleteSignUpRequest, onResult: (AthleteSignUpResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AthleteEndpoints::class.java)
        retrofit.signUpAthlete(athleteSignUpRequest).enqueue(
            object : Callback<AthleteSignUpResponse> {
                override fun onFailure(call: Call<AthleteSignUpResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("SignUp failed , check your information and try again.")
                        .setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                    onResult(null)
                }
                override fun onResponse(call: Call<AthleteSignUpResponse>, response: Response<AthleteSignUpResponse>) {
                    val body = response.body()
                    if(body!=null){
                        onResult(body)
                    }
                    else{
                        val gson = Gson()
                        val error: ErrorResponse = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            ErrorResponse::class.java
                        )
                        MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle("Signup Failed")
                            .setMessage(error.message)
                            .setPositiveButton("OK",
                                DialogInterface.OnClickListener { dialogInterface, i ->  })
                            .show()
                        onResult(null)
                    }
                    Log.e("onResponse",body.toString())
                }
            }
        )
    }

    fun logInAthlete(athleteLogInRequest: AthleteLogInRequest, onResult: (AthleteLogInResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(AthleteEndpoints::class.java)
        retrofit.logInAthlete(athleteLogInRequest).enqueue(
            object : Callback<AthleteLogInResponse> {
                override fun onFailure(call: Call<AthleteLogInResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("Login failed , check your information and try again.")
                        .setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                    onResult(null)
                }
                override fun onResponse(call: Call<AthleteLogInResponse>, response: Response<AthleteLogInResponse>) {
                    val body = response.body()
                    if(body!=null){
                        onResult(body)
                    }
                    else{
                        val gson = Gson()
                        val error: ErrorResponse = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            ErrorResponse::class.java
                        )
                        MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle("Login Failed")
                            .setMessage(error.message)
                            .setPositiveButton("OK",
                                DialogInterface.OnClickListener { dialogInterface, i ->  })
                            .show()
                        onResult(null)
                    }

                    Log.e("onResponse",body.toString())
                }
            }
        )
    }
}