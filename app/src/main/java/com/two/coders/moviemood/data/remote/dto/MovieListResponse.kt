package com.tta.thisweektvshows.api.codables

import com.google.gson.annotations.SerializedName
import com.two.coders.moviemood.data.remote.dto.MovieResponse

data class MovieListResponse(
    var page: Int,
    @SerializedName("results")
    var results: ArrayList<MovieResponse>
)