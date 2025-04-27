package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.MovieReview
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult

class GetMovieReviewsUseCase(private val repository: MoviesRepository) {
    suspend fun invoke(movieId: Int): AppResult<ArrayList<MovieReview>> =
        repository.getMovieReviews(movieId)
}