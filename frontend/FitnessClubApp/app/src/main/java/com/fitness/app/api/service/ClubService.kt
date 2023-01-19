package com.fitness.app.api.service

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import com.fitness.app.R
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.ClubEndpoints
import com.fitness.app.model.api.request.club.ClubLogInRequest
import com.fitness.app.model.api.request.club.ClubSignUpRequest
import com.fitness.app.model.api.response.club.ClubLogInResponse
import com.fitness.app.model.api.response.club.ClubSignUpResponse
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubService(val context: Context) {
    fun signUpClub(clubSignUpRequest: ClubSignUpRequest, onResult: (ClubSignUpResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.signUpClub(clubSignUpRequest).enqueue(
            object : Callback<ClubSignUpResponse> {
                override fun onFailure(call: Call<ClubSignUpResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("SignUp failed , check your information and try again.")
                        .setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                    onResult(null)
                }
                override fun onResponse(call: Call<ClubSignUpResponse>, response: Response<ClubSignUpResponse>) {
                    val statusCode = response.code()
                    Log.e("status code : ",statusCode.toString())
                    if(statusCode==201){
                        // success -> go to home
                        val activity = context as Activity
                        val intent = Intent(activity, AthleteHomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        context.startActivity(intent)
                    }
                    else{
                        MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle("Failed!")
                            .setMessage("SignUp failed , check your information and try again.")
                            .setPositiveButton("OK",
                                DialogInterface.OnClickListener { dialogInterface, i ->  })
                            .show()
                    }
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun logInClub(clubLogInRequest: ClubLogInRequest, onResult: (ClubLogInResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.logInClub(clubLogInRequest).enqueue(
            object : Callback<ClubLogInResponse> {
                override fun onFailure(call: Call<ClubLogInResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("Login failed , check your information and try again.")
                        .setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                    onResult(null)
                }
                override fun onResponse(call: Call<ClubLogInResponse>, response: Response<ClubLogInResponse>) {
                    val statusCode = response.code()
                    Log.e("status code : ",statusCode.toString())
                    if(statusCode==200){
                        // success -> go to home
                        val activity = context as Activity
                        val intent = Intent(activity, AthleteHomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        context.startActivity(intent)

                    }
                    else{
                        MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle("Failed!")
                            .setMessage("Login failed , check your information and try again.")
                            .setPositiveButton("OK",
                                DialogInterface.OnClickListener { dialogInterface, i ->  })
                            .show()
                    }
                    val body = response.body()

                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }
}