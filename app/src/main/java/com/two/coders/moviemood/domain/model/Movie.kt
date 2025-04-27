package com.two.coders.moviemood.domain.model

data class Movie(
    var id: Int = -1,
    var name: String? = "",
    var email: String? = "",
    var posterPath: String? = "",
    var overview: String? = "",
    var genres: List<Genre> = emptyList()
)