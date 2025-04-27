package com.two.coders.moviemood.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.two.coders.moviemood.presentation.ui.components.MovieItem

@Composable
fun HomeScreen(viewModel: HomeViewModel, onMovieClick: (Int) -> Unit, onSearchClick: () -> Unit) {
    val state by viewModel.state.collectAsState()
    HomeScreenUI(
        state = state,
        onMovieClick = onMovieClick,
        onSearchClick = onSearchClick,
        fetchMoviesClick = { viewModel.fetchMovies() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    state: HomeUiState,
    onMovieClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    fetchMoviesClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Movie Mood") }, actions = {
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            })
        }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading && state.movies.isEmpty() -> {
                    CircularProgressIndicator()
                }

                state.error != null -> {
                    Text(
                        text = state.error.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        itemsIndexed(state.movies.toList()) { index, movie ->
                            MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })

                            if (index >= state.movies.lastIndex - 5) {
                                // Trigger load more near the end
                                LaunchedEffect(Unit) {
                                    fetchMoviesClick.invoke()
                                }
                            }
                        }

                        if (state.isLoading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenUI(
        state = HomeUiState(
            isLoading = false,
            movies = arrayListOf(),
            error = null
        ),
        onMovieClick = {},
        onSearchClick = {},
        fetchMoviesClick = {}
    )
}
