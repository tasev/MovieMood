package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.Result

class GetMoviesUseCase(private val repository: MoviesRepository) {
    suspend fun invoke(): Result<ArrayList<Movie>> = repository.getMovies()
}