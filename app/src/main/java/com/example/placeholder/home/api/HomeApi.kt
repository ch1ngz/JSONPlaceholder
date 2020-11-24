package com.example.placeholder.home.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

    @GET("posts/{id}")
    fun getPost(@Path("id") postId: Long): Single<PostResponse>
}