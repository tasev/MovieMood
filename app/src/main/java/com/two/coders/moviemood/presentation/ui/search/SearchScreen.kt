package com.two.coders.moviemood.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.two.coders.moviemood.presentation.ui.components.MovieItem

@Composable
fun SearchScreen(viewModel: SearchViewModel, onMovieClick: (Int) -> Unit) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = state.query,
                onValueChange = viewModel::updateQuery,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

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
