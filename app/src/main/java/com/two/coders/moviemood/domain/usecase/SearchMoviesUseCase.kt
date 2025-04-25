package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

class SearchMoviesUseCase(private val repository: MoviesRepository) {
    suspend fun invoke(query: String): AppResult<ArrayList<Movie>> = repository.searchMovies(query)
}