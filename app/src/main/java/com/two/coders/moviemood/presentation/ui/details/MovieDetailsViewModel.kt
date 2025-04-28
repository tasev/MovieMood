package com.two.coders.moviemood.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.two.coders.moviemood.di.IoDispatcher
import com.two.coders.moviemood.domain.usecase.GetMovieDetailsUseCase
import com.two.coders.moviemood.domain.usecase.GetMovieReviewsUseCase
import com.two.coders.moviemood.utils.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Movie Details screen.
 * Manages the state of movie details and reviews, and handles fetching them from the repository.
 * @param getMovieDetailsUseCase The use case for fetching movie details.
 * @param getMovieReviewsUseCase The use case for fetching movie reviews.
 * @param dispatcher The coroutine dispatcher for background operations.
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsUiState())
    val state: StateFlow<MovieDetailsUiState> = _state

    /**
     * Fetches the details and reviews of a movie by its ID.
     * Updates the state with the results or errors.
     * @param movieId The ID of the movie to fetch details for.
     */
    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch(dispatcher) {
            _state.value = _state.value.copy(movie = null, isLoading = true)

            val detailsDeferred = async { getMovieDetailsUseCase.invoke(movieId) }
            val reviewsDeferred = async { getMovieReviewsUseCase.invoke(movieId) }

            when (val result = detailsDeferred.await()) {
                is AppResult.Success -> _state.value = _state.value.copy(
                    movie = result.data,
                    isLoading = false,
                    error = null
                )

                is AppResult.Error -> _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message
                )

                AppResult.Loading -> { /* Already handled */
                }
            }

            when (val result = reviewsDeferred.await()) {
                is AppResult.Success -> {
                    _state.value = _state.value.copy(
                        movieReviews = result.data
                    )
                }

                is AppResult.Error -> { /* Ignore review errors */
                }

                AppResult.Loading -> { /* Already handled */
                }
            }
        }
    }
}
