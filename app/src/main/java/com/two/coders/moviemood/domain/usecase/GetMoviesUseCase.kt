package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository) {
    suspend fun invoke(): ArrayList<Movie> = repository.getMovies()
}