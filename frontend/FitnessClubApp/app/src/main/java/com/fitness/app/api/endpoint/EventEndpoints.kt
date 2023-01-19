package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.diet.PickDietRequest
import com.fitness.app.model.api.request.event.DiscoverEventsRequest
import com.fitness.app.model.api.request.event.ParticipateInEventRequest
import com.fitness.app.model.api.response.diet.*
import com.fitness.app.model.api.response.event.DiscoverEventsResponse
import com.fitness.app.model.api.response.event.GetEventByIdResponse
import com.fitness.app.model.api.response.event.GetEventCommentsResponse
import com.fitness.app.model.api.response.event.ParticipateInEventResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventEndpoints {
    @POST("event/discover")
    fun discoverEvents(@Body discoverEventsRequest: DiscoverEventsRequest) : Call<DiscoverEventsResponse>

    @GET("event/{event_id}")
    fun getEventById(@Path(value = "event_id", encoded = true) eventId:String) : Call<GetEventByIdResponse>

    @GET("event/{event_id}/comments")
    fun getEventComments(@Path(value = "event_id", encoded = true) eventId:String) : Call<GetEventCommentsResponse>

//    @GET("event/{event_id}/cover")
//    fun getEventCoverPicture(@Path(value = "event_id", encoded = true) eventId:String) : Call<GetEventCoverPictureResponse>

    @POST("event/{event_id}/participate")
    fun participateEvent(@Path(value = "event_id", encoded = true) eventId:String , @Body participateInEventRequest: ParticipateInEventRequest) : Call<ParticipateInEventResponse>
}