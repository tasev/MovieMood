package com.two.coders.moviemood.presentation.ui.home

import com.two.coders.moviemood.domain.model.Movie

data class HomeUiState(
    val movies: ArrayList<Movie> = arrayListOf()
)