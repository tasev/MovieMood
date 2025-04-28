package com.two.coders.moviemood.presentation.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.two.coders.moviemood.R
import com.two.coders.moviemood.presentation.ui.components.MovieReviewItem
import com.two.coders.moviemood.utils.SharedPref

/**
 * Composable function for the Movie Details screen.
 * Displays the details and reviews of a specific movie.
 * @param viewModel The ViewModel managing the state of the Movie Details screen.
 * @param movieId The ID of the movie to display details for.
 * @param onBackClick Callback invoked when the back button is clicked.
 */
@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel, movieId: Int, onBackClick: () -> Unit) {

    val state by viewModel.state.collectAsState()

    val myFavouriteMovies: HashSet<String> = SharedPref.myFavouriteMovies.toHashSet()

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetail(movieId)
    }
    MovieDetailsScreenUI(state, movieId, myFavouriteMovies, onBackClick)
}

/**
 * UI for the Movie Details screen.
 * Displays a header image, genres, title, description, and reviews of the movie.
 * Allows marking the movie as a favorite.
 * @param state The UI state of the Movie Details screen.
 * @param movieId The ID of the movie being displayed.
 * @param myFavouriteMovies A set of favorite movie IDs.
 * @param onBackClick Callback invoked when the back button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreenUI(
    state: MovieDetailsUiState,
    movieId: Int,
    myFavouriteMovies: HashSet<String>,
    onBackClick: () -> Unit
) {
    var isOneOfMyFavouriteMovies by remember { mutableStateOf(myFavouriteMovies.contains("$movieId")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { state.movie?.name ?: "" },
                navigationIcon = {
                    IconButton(onClick = { onBackClick.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {
            item {
                // Header image with overlay
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = state.movie?.posterPath ?: "",
                        contentDescription = "Header image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                shape = RoundedCornerShape(
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp
                                )
                            ),
                        contentScale = ContentScale.Crop
                    )
                    AsyncImage(
                        model = if (isOneOfMyFavouriteMovies) R.drawable.heart else R.drawable.heart_outline,
                        contentDescription = "is Favourite image",
                        modifier = Modifier
                            .padding(16.dp)
                            .width(40.dp)
                            .height(40.dp)
                            .align(Alignment.TopEnd)
                            .clickable {
                                if (isOneOfMyFavouriteMovies) {
                                    myFavouriteMovies.remove("$movieId")
                                } else {
                                    myFavouriteMovies.add("$movieId")
                                }
                                isOneOfMyFavouriteMovies = !isOneOfMyFavouriteMovies
                                SharedPref.myFavouriteMovies = myFavouriteMovies
                            }
                    )
                }
                // Hashtags for genres
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    state.movie?.genres?.forEach { genre ->
                        Text(
                            text = "#${genre.name}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .padding(end = 8.dp)
                        )
                    }
                }
                // Title
                Text(
                    text = state.movie?.name ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(10.dp)
                )

                // Description
                Text(
                    text = state.movie?.overview ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
                )

                state.movieReviews?.let {
                    this@LazyColumn.itemsIndexed(it.toList()) { index, review ->
                        MovieReviewItem(movieReview = review)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreenUI(
        state = MovieDetailsUiState(),
        movieId = 1,
        myFavouriteMovies = HashSet<String>(),
    ) {}
}