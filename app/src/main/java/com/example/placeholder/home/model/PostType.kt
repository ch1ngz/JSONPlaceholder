package com.example.placeholder.home.model

sealed class PostType {
    object NotFilled : PostType()
    data class Filled(val title: String, val body: String) : PostType()
    data class Error(val throwable: Throwable) : PostType()
}