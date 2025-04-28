package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

/**
 * Use case for fetching a list of movies.
 * @param repository The repository to fetch movies from.
 */
class GetMoviesUseCase(private val repository: MoviesRepository) {
    /**
     * Invokes the use case to fetch movies for the given page.
     * @param currentPage The page number to fetch movies for.
     * @return [AppResult] containing a list of movies or an error.
     */
    suspend fun invoke(currentPage: Int): AppResult<ArrayList<Movie>> = repository.getMovies(currentPage)
}