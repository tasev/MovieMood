package com.two.coders.moviemood.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val isFavorite: Boolean = false
)