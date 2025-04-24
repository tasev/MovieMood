package com.two.coders.moviemood.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.two.coders.moviemood.domain.usecase.GetMoviesUseCase
import com.two.coders.moviemood.utils.Result
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

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _state.value = state.value.copy(movies = arrayListOf(), isLoading = true)
            when (val result = getMoviesUseCase.invoke()) {
                is Result.Success -> _state.value =
                    state.value.copy(movies = result.data, isLoading = false)

                is Result.Error -> _state.value =
                    state.value.copy(isLoading = false, error = result.message)

                else -> Unit
            }
        }
    }
}