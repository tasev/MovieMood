package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

class GetMovieDetailsUseCase(private val repository: MoviesRepository) {
    suspend fun invoke(movieId: Int): AppResult<Movie> = repository.getMovieDetails(movieId)
}