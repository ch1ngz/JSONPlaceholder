package com.example.placeholder.home.interactor

import com.example.placeholder.home.model.PostType
import com.example.placeholder.home.repository.HomeRepository
import io.reactivex.Single

class HomeInteractor(
    private val homeRepository: HomeRepository
) {

    fun checkPost(postId: Long): Single<PostType> =
        homeRepository.getPostById(postId)
            .flatMap { post ->
                val result = if (post.title.isNullOrBlank() || post.body.isNullOrBlank()) {
                    PostType.NotFilled
                } else {
                    PostType.Filled(post.title, post.body)
                }

                Single.just(result)
            }
}