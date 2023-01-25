package com.fitness.app.api.service

import android.content.Context
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.MeasurementEndpoints
import com.fitness.app.model.api.request.measurement.UpdateMeasurementRequest
import com.fitness.app.model.api.response.measurement.UpdateMeasurementResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MeasurementService(val context: Context) {
    fun updateMeasurement(measurementId:String , updateMeasurementRequest: UpdateMeasurementRequest, onResult: (UpdateMeasurementResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(MeasurementEndpoints::class.java)
        retrofit.updateMeasurement(measurementId,updateMeasurementRequest).enqueue(
            object : Callback<UpdateMeasurementResponse> {
                override fun onFailure(call: Call<UpdateMeasurementResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<UpdateMeasurementResponse>, response: Response<UpdateMeasurementResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }
}