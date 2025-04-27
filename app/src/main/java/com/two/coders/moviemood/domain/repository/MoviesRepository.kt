package com.two.coders.moviemood.domain.repository

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.model.MovieReview
import com.two.coders.moviemood.utils.AppResult

interface MoviesRepository {
    suspend fun getMovies(currentPage: Int): AppResult<ArrayList<Movie>>
    suspend fun getMovieDetails(movieId: Int): AppResult<Movie>
    suspend fun getMovieReviews(movieId: Int): AppResult<ArrayList<MovieReview>>
    suspend fun searchMovies(query: String): AppResult<ArrayList<Movie>>
}