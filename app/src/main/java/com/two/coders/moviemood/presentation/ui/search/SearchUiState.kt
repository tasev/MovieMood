package com.two.coders.moviemood.presentation.ui.search

import com.two.coders.moviemood.domain.model.Movie

data class SearchUiState(
    val query: String = "",
    val mediaType: String = "movie",
    val movies: ArrayList<Movie> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)