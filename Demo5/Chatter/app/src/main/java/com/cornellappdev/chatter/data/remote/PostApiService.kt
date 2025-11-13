package com.cornellappdev.chatter.data.remote

import com.cornellappdev.chatter.data.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiService {
    @GET("posts")
    suspend fun getAllPosts(): PostResponse

    @GET("posts/search")
    suspend fun searchPosts(@Query("q") query: String): PostResponse
}

