package com.cornellappdev.chatter.data.repository

import com.cornellappdev.chatter.data.model.Post
import com.cornellappdev.chatter.data.remote.PostApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val postApiService: PostApiService
) {
    suspend fun getAllPosts(): Result<List<Post>> {
        return try {
            val response = postApiService.getAllPosts()
            Result.success(response.posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun searchPosts(query: String): Result<List<Post>> {
        return try {
            val response = postApiService.searchPosts(query)
            Result.success(response.posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

