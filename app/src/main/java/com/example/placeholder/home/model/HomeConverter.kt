package com.example.placeholder.home.model

import com.example.placeholder.home.api.PostResponse

object HomeConverter {

    fun fromNetwork(response: PostResponse) =
        Post(
            id = response.id,
            userId = response.userId,
            title = response.title,
            body = response.body,
        )
}