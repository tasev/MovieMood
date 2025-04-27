package com.two.coders.moviemood.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieReviewsListResponse(
    var page: Int,
    @SerializedName("results")
    var results: ArrayList<MovieReviewResponse>
)