package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.EventEndpoints
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.event.ParticipateInEventRequest
import com.fitness.app.model.api.response.event.DiscoverEventsResponse
import com.fitness.app.model.api.response.event.GetEventByIdResponse
import com.fitness.app.model.api.response.event.GetEventCommentsResponse
import com.fitness.app.model.api.response.event.ParticipateInEventResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventService (val context: Context) {
    fun participateEvent(eventId:String , participateInEventRequest: ParticipateInEventRequest, onResult: (ParticipateInEventResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(EventEndpoints::class.java)
        retrofit.participateEvent(eventId,participateInEventRequest).enqueue(
            object : Callback<ParticipateInEventResponse> {
                override fun onFailure(call: Call<ParticipateInEventResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<ParticipateInEventResponse>, response: Response<ParticipateInEventResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun getEventCoverPicture(eventId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(EventEndpoints::class.java)
        retrofit.getEventCoverPicture(eventId).enqueue(
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
                    else Log.e("EventCoverPic Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("EventCoverPic fail","EventCoverPic fail")
                    onResult(null)
                }

            }
        )
    }

    fun getEventComments(eventId: String, onResult: (GetEventCommentsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(EventEndpoints::class.java)
        retrofit.getEventComments(eventId).enqueue(
            object : Callback<GetEventCommentsResponse> {
                override fun onFailure(call: Call<GetEventCommentsResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetEventCommentsResponse>, response: Response<GetEventCommentsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getEventById(eventId: String, onResult: (GetEventByIdResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(EventEndpoints::class.java)
        retrofit.getEventById(eventId).enqueue(
            object : Callback<GetEventByIdResponse> {
                override fun onFailure(call: Call<GetEventByIdResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetEventByIdResponse>, response: Response<GetEventByIdResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun discoverEvents(discoverEventsRequest: DiscoverEventsRequest, onResult: (DiscoverEventsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(EventEndpoints::class.java)
        retrofit.discoverEvents(discoverEventsRequest).enqueue(
            object : Callback<DiscoverEventsResponse> {
                override fun onFailure(call: Call<DiscoverEventsResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<DiscoverEventsResponse>, response: Response<DiscoverEventsResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }
}