package com.two.coders.moviemood.domain.model

data class MovieReview(
    val id: String,
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val updatedAt: String
)