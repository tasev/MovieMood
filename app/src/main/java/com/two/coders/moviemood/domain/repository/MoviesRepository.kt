package com.two.coders.moviemood.domain.repository

import com.two.coders.moviemood.domain.model.Movie

interface MoviesRepository {
    suspend fun getMovies(): ArrayList<Movie>
}