package com.two.coders.moviemood.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.two.coders.moviemood.presentation.ui.components.MovieItem

@Composable
fun SearchScreen(viewModel: SearchViewModel, onMovieClick: (Int) -> Unit, onBackClick: () -> Unit) {
    val state by viewModel.state.collectAsState()
    SearchScreenUI(
        state = state,
        onMovieClick = onMovieClick,
        onBackClick = onBackClick,
        updateQuery = viewModel::updateQuery
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenUI(
    state: SearchUiState,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    updateQuery: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* Empty title for now */ },
                navigationIcon = {
                    IconButton(onClick = { onBackClick.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp, start = 48.dp) // optional padding
                    ) {
                        TextField(
                            value = state.query,
                            onValueChange = updateQuery,
                            placeholder = { Text("Search...") },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {

            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            LazyColumn {
                itemsIndexed(state.movies.toList()) { index, movie ->
                    MovieItem(movie = movie, onClick = { onMovieClick(movie.id) })
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreenUI(
        state = SearchUiState(
            isLoading = false,
            movies = arrayListOf(),
            query = ""
        ),
        onMovieClick = {},
        onBackClick = {},
        updateQuery = {}
    )
}
