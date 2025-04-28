package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

/**
 * Use case for searching movies by a query string.
 * @param repository The repository to search movies from.
 */
class SearchMoviesUseCase(private val repository: MoviesRepository) {
    /**
     * Invokes the use case to search for movies based on a query.
     * @param query The search query string.
     * @return [AppResult] containing a list of movies or an error.
     */
    suspend fun invoke(query: String): AppResult<ArrayList<Movie>> = repository.searchMovies(query)
}