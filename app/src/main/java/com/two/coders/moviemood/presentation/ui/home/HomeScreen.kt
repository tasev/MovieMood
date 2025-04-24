package com.two.coders.moviemood.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.two.coders.moviemood.R

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            if (state.isLoading) stringResource(R.string.loading_label) else if (state.error != null) state.error.toString() else "Number of movies loaded, ${state.movies.size}!",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}