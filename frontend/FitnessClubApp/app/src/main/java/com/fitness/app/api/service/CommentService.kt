package com.fitness.app.api.service

import android.content.Context
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.CommentEndpoints
import com.fitness.app.model.api.request.comment.CreateCommentRequest
import com.fitness.app.model.api.request.comment.UpdateCommentRequest
import com.fitness.app.model.api.response.comment.CreateCommentResponse
import com.fitness.app.model.api.response.comment.DeleteCommentResponse
import com.fitness.app.model.api.response.comment.UpdateCommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentService(val context: Context) {
    fun deleteComment(commentId: String, onResult: (DeleteCommentResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(CommentEndpoints::class.java)
        retrofit.deleteComment(commentId).enqueue(
            object : Callback<DeleteCommentResponse> {
                override fun onFailure(call: Call<DeleteCommentResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<DeleteCommentResponse>, response: Response<DeleteCommentResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun updateComment(commentId:String , updateCommentRequest: UpdateCommentRequest, onResult: (UpdateCommentResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(CommentEndpoints::class.java)
        retrofit.updateComment(commentId , updateCommentRequest).enqueue(
            object : Callback<UpdateCommentResponse> {
                override fun onFailure(call: Call<UpdateCommentResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<UpdateCommentResponse>, response: Response<UpdateCommentResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun createComment(createCommentRequest: CreateCommentRequest, onResult: (CreateCommentResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(CommentEndpoints::class.java)
        retrofit.createComment(createCommentRequest).enqueue(
            object : Callback<CreateCommentResponse> {
                override fun onFailure(call: Call<CreateCommentResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<CreateCommentResponse>, response: Response<CreateCommentResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }
}