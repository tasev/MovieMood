package com.two.coders.moviemood.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.two.coders.moviemood.domain.model.Movie

data class MovieResponse(
    @SerializedName("id")
    var id: Int? = -1,
    @SerializedName("name")
    var name: String? = "",
    var email: String? = "",
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @SerializedName("overview")
    var overview: String? = ""

) {
    fun toDomain() =
        Movie(id = id, name = name, email = email, posterPath = posterPath, overview = overview)
}