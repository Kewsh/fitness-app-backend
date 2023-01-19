package com.fitness.app.api

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import com.fitness.app.R
import com.fitness.app.model.api.request.AthleteLogInRequest
import com.fitness.app.model.api.request.AthleteSignUpRequest
import com.fitness.app.model.api.response.AthleteLogInResponse
import com.fitness.app.model.api.response.AthleteSignUpResponse
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIService(val context: Context) {
    fun signUpAthlete(athleteSignUpRequest: AthleteSignUpRequest, onResult: (AthleteSignUpResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        retrofit.signUpAthlete(athleteSignUpRequest).enqueue(
            object : Callback<AthleteSignUpResponse> {
                override fun onFailure(call: Call<AthleteSignUpResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("SignUp failed , check your information and try again.")
                        .setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                    onResult(null)
                }
                override fun onResponse(call: Call<AthleteSignUpResponse>, response: Response<AthleteSignUpResponse>) {
                    val statusCode = response.code()
                    Log.e("status code : ",statusCode.toString())
                    if(statusCode==201){
                        // success -> go to home
                        val activity = context as Activity
                        val intent = Intent(activity,AthleteHomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        context.startActivity(intent)
                    }
                    else{
                        MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle("Failed!")
                            .setMessage("SignUp failed , check your information and try again.")
                            .setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->  })
                            .show()
                    }
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun logInAthlete(athleteLogInRequest: AthleteLogInRequest, onResult: (AthleteLogInResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        retrofit.logInAthlete(athleteLogInRequest).enqueue(
            object : Callback<AthleteLogInResponse> {
                override fun onFailure(call: Call<AthleteLogInResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                        .setTitle("Failed!")
                        .setMessage("Login failed , check your information and try again.")
                        .setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->  })
                        .show()
                    onResult(null)
                }
                override fun onResponse(call: Call<AthleteLogInResponse>, response: Response<AthleteLogInResponse>) {
                    val statusCode = response.code()
                    Log.e("status code : ",statusCode.toString())
                    if(statusCode==200){
                        // success -> go to home
                        val activity = context as Activity
                        val intent = Intent(activity,AthleteHomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        context.startActivity(intent)

                    }
                    else{
                        MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme)
                            .setTitle("Failed!")
                            .setMessage("Login failed , check your information and try again.")
                            .setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->  })
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