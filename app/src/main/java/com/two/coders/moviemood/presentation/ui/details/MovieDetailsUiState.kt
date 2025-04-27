package com.two.coders.moviemood.presentation.ui.details

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.model.MovieReview

data class MovieDetailsUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val movieReviews: List<MovieReview>? = null
)