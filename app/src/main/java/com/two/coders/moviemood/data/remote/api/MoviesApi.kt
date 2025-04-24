package com.two.coders.moviemood.data.remote.api

import com.tta.thisweektvshows.api.codables.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @Headers("Accept: application/json")
    @GET("trending/{media_type}/{time_window}")
   suspend fun getMovies(
        @Path("media_type") mediaType: String = "movie",
        @Path("time_window") timeWindow: String = "week",
        @Query("api_key") authHeader: String = "ded5e14622d8004a564b41c4e2beaef4"
    ): MovieListResponse
}