package com.pr7.jc_retrofit_mvi_jsonplaceholder.data.remote


import com.pr7.jc_retrofit_mvi_jsonplaceholder.model.NewPost
import com.pr7.jc_retrofit_mvi_jsonplaceholder.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface ApiService {
    @GET("posts/1")
    suspend fun getPost(): Post

    @POST("posts")
    suspend fun createPost(@Body newPost: NewPost): Post
}