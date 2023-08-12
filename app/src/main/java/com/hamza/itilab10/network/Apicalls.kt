package com.hamza.itilab10.network

import com.hamza.itilab10.models.ModelComments
import com.hamza.itilab10.models.ModelPosts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCalls {
    @GET("posts")
    suspend fun getPosts(@Query("userId") userId: String): Response<ModelPosts>

    @GET("posts/{post_id}/comments")
    suspend fun getComments(@Path("post_id") postId: Int):Response<ModelComments>
}