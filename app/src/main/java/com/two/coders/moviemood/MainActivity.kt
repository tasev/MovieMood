package com.two.coders.moviemood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.two.coders.moviemood.data.repository.MoviesRepositoryImpl
import com.two.coders.moviemood.domain.usecase.GetMoviesUseCase
import com.two.coders.moviemood.presentation.navigation.AppNavGraph
import com.two.coders.moviemood.presentation.theme.MovieMoodTheme
import com.two.coders.moviemood.presentation.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()
            MovieMoodTheme {
                AppNavGraph(navController)
            }
        }
    }
}