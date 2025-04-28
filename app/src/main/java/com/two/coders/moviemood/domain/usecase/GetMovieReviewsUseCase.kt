package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.MovieReview
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

/**
 * Use case for fetching reviews of a specific movie.
 * @param repository The repository to fetch movie reviews from.
 */
class GetMovieReviewsUseCase(private val repository: MoviesRepository) {
    /**
     * Invokes the use case to fetch reviews of a movie by its ID.
     * @param movieId The ID of the movie to fetch reviews for.
     * @return [AppResult] containing a list of reviews or an error.
     */
    suspend fun invoke(movieId: Int): AppResult<ArrayList<MovieReview>> =
        repository.getMovieReviews(movieId)
}