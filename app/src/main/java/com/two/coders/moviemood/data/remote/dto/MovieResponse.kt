package com.two.coders.moviemood.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.two.coders.moviemood.domain.model.Genre
import com.two.coders.moviemood.domain.model.Movie

data class MovieResponse(
    @SerializedName("id")
    var id: Int = -1,
    @SerializedName("title")
    var name: String? = "",
    var email: String? = "",
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @SerializedName("overview")
    var overview: String? = "",
    @SerializedName("genres")
    var genres: List<GenreResponse> = emptyList()
) {

    fun toDomain() = Movie(
        id = id,
        name = name,
        email = email,
        posterPath = "https://image.tmdb.org/t/p/w500${this.posterPath}",
        overview = overview,
        genres = genres.map { Genre(id = it.id, name = it.name) }
    )
}