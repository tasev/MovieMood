package com.two.coders.moviemood.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.two.coders.moviemood.domain.usecase.GetMoviesUseCase
import com.two.coders.moviemood.utils.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state

    private var currentPage = 1
    private var isLastPage = false
    private var isLoadingMore = false

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        if (isLoadingMore || isLastPage) return
        isLoadingMore = true

        viewModelScope.launch {
            if (currentPage == 1) {
                _state.value = _state.value.copy(movies = arrayListOf(), isLoading = true)
            }

            when (val result = getMoviesUseCase.invoke(currentPage)) {
                is AppResult.Success -> {
                    val newMovies = result.data
                    val updatedMovies = ArrayList(_state.value.movies).apply {
                        addAll(newMovies)
                    }

                    _state.value = _state.value.copy(
                        movies = updatedMovies,
                        isLoading = false,
                        error = null
                    )

                    currentPage++
                    if (newMovies.isEmpty()) isLastPage = true
                }

                is AppResult.Error -> _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message
                )

                AppResult.Loading -> {
                    // Loading state is already handled in the fetchMovies method
                }
            }

            isLoadingMore = false
        }
    }
}
