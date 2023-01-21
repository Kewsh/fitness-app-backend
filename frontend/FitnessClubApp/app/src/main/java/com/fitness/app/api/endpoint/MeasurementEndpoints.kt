package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.measurement.UpdateMeasurementRequest
import com.fitness.app.model.api.response.measurement.UpdateMeasurementResponse
import retrofit2.Call
import retrofit2.http.*

interface MeasurementEndpoints {
    @PATCH("measurement/{measurement_id}")
    fun updateMeasurement(@Path(value = "measurement_id", encoded = true) measurementId:String , @Body updateMeasurementRequest: UpdateMeasurementRequest) : Call<UpdateMeasurementResponse>
}