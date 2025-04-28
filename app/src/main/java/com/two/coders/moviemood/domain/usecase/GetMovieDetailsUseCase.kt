package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

/**
 * Use case for fetching details of a specific movie.
 * @param repository The repository to fetch movie details from.
 */
class GetMovieDetailsUseCase(private val repository: MoviesRepository) {
    /**
     * Invokes the use case to fetch details of a movie by its ID.
     * @param movieId The ID of the movie to fetch details for.
     * @return [AppResult] containing the movie details or an error.
     */
    suspend fun invoke(movieId: Int): AppResult<Movie> = repository.getMovieDetails(movieId)
}