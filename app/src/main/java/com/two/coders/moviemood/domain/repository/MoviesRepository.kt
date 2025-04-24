package com.two.coders.moviemood.domain.repository

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.utils.Result

interface MoviesRepository {
    suspend fun getMovies(): Result<ArrayList<Movie>>
}