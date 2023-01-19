package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.comment.CreateCommentRequest
import com.fitness.app.model.api.request.comment.UpdateCommentRequest
import com.fitness.app.model.api.response.comment.CreateCommentResponse
import com.fitness.app.model.api.response.comment.DeleteCommentResponse
import com.fitness.app.model.api.response.comment.UpdateCommentResponse
import retrofit2.Call
import retrofit2.http.*

interface CommentEndpoints {
    @POST("comment")
    fun createComment(@Body createCommentRequest: CreateCommentRequest) : Call<CreateCommentResponse>

    @PATCH("comment/{comment_id}")
    fun updateComment(@Path(value = "comment_id", encoded = true) commentId:String , @Body updateCommentRequest: UpdateCommentRequest) : Call<UpdateCommentResponse>

    @DELETE("comment/{comment_id}")
    fun deleteComment(@Path(value = "comment_id", encoded = true) commentId: String) : Call<DeleteCommentResponse>
}