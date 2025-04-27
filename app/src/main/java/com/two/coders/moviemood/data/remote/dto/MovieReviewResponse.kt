package com.two.coders.moviemood.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.two.coders.moviemood.domain.model.AuthorDetails
import com.two.coders.moviemood.domain.model.MovieReview

data class MovieReviewResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetailsResponse,
    @SerializedName("content")
    val content: String,
    @SerializedName("updated_at")
    val updatedAt: String
) {
    fun toDomain() = MovieReview(
        id = id,
        author = author,
        authorDetails = AuthorDetails(
            name = authorDetails.name,
            avatarPath = "https://image.tmdb.org/t/p/w200${authorDetails.avatarPath}",
            username = authorDetails.username,
            rating = authorDetails.rating
        ),
        content = content,
        updatedAt = updatedAt
    )
}