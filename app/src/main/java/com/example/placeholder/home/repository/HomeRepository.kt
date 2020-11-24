package com.example.placeholder.home.repository

import com.example.placeholder.home.api.HomeApi
import com.example.placeholder.home.model.HomeConverter
import com.example.placeholder.home.model.Post
import io.reactivex.Single

interface HomeRepository {
    fun getPostById(postId: Long): Single<Post>
}

class HomeRepositoryImpl(
    private val homeApi: HomeApi
) : HomeRepository {

    override fun getPostById(postId: Long): Single<Post> =
        homeApi.getPost(postId).map {
            HomeConverter.fromNetwork(it)
        }
}