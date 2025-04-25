package com.two.coders.moviemood.presentation.ui.details

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel, movieId: Int) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetail(movieId)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            // Header image
            AsyncImage(
                model = state.movie?.posterPath ?: "",
                contentDescription = "Header image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.6f)
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
                contentScale = ContentScale.Crop
            )
            // Title
            Text(
                text = viewModel.state.value.movie?.name ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(10.dp)
            )

            // Description
            Text(
                text = state.movie?.overview ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
            )
        }
    }
}