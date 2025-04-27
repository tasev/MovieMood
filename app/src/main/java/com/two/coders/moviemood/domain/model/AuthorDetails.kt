package com.two.coders.moviemood.domain.model

data class AuthorDetails(
    val name: String,
    val username: String,
    val avatarPath: String? = null,
    val rating: String
)