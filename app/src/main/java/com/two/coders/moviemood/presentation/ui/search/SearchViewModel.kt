package com.two.coders.moviemood.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.usecase.SearchMoviesUseCase
import com.two.coders.moviemood.utils.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(400L)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.length > 2) {
                        performSearch(query)
                    } else {
                        _state.value = _state.value.copy(movies = arrayListOf(), error = null)
                    }
                }
        }
    }

    fun updateQuery(newQuery: String) {
        _state.value = _state.value.copy(query = newQuery)
        searchQuery.value = newQuery
    }

    private suspend fun performSearch(query: String) {
        _state.value = _state.value.copy(isLoading = true)
        when (val result = searchMoviesUseCase.invoke(query)) {
            is AppResult.Success<ArrayList<Movie>> -> _state.value =
                _state.value.copy(movies = result.data, isLoading = false)

            is AppResult.Error -> _state.value =
                _state.value.copy(error = result.message, isLoading = false)

            AppResult.Loading -> {}
        }
    }
}