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
import com.fitness.app.api.endpoint.ClubEndpoints
import com.fitness.app.model.api.request.club.ClubLogInRequest
import com.fitness.app.model.api.request.club.ClubSignUpRequest
import com.fitness.app.model.api.response.club.*
import com.fitness.app.views.activities.AthleteHomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubService(val context: Context) {
    fun getClubPrograms(clubId: String, onResult: (GetClubProgramsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.getClubPrograms(clubId).enqueue(
            object : Callback<GetClubProgramsResponse> {
                override fun onFailure(call: Call<GetClubProgramsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetClubProgramsResponse>, response: Response<GetClubProgramsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getClubEvents(clubId: String, onResult: (GetClubEventsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.getClubEvents(clubId).enqueue(
            object : Callback<GetClubEventsResponse> {
                override fun onFailure(call: Call<GetClubEventsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetClubEventsResponse>, response: Response<GetClubEventsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getClubById(clubId: String, onResult: (GetClubByIdResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.getClubById(clubId).enqueue(
            object : Callback<GetClubByIdResponse> {
                override fun onFailure(call: Call<GetClubByIdResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetClubByIdResponse>, response: Response<GetClubByIdResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getClubLogo(clubId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.getClubLogo(clubId).enqueue(
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
                    else Log.e("ClubLogo Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("getClubLogo fail","getClubLogo fail")
                    onResult(null)
                }

            }
        )
    }

    fun getClubCoverPicture(clubId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ClubEndpoints::class.java)
        retrofit.getClubCoverPicture(clubId).enqueue(
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
                    else Log.e("ClubCoverPicture Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("getClubCoverPic fail","getClubCoverPic fail")
                    onResult(null)
                }

            }
        )
    }

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