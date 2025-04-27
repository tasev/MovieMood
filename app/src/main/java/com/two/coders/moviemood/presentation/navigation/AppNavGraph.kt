package com.two.coders.moviemood.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.two.coders.moviemood.presentation.ui.details.MovieDetailsScreen
import com.two.coders.moviemood.presentation.ui.details.MovieDetailsViewModel
import com.two.coders.moviemood.presentation.ui.home.HomeScreen
import com.two.coders.moviemood.presentation.ui.home.HomeViewModel
import com.two.coders.moviemood.presentation.ui.search.SearchScreen
import com.two.coders.moviemood.presentation.ui.search.SearchViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val searchViewModel: SearchViewModel = hiltViewModel()
    val movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = homeViewModel,
                onMovieClick = { movieId ->
                    navController.navigate("movieDetails/$movieId")
                },
                onSearchClick = {
                    navController.navigate("search")
                }
            )
        }

        composable("search") {
            SearchScreen(
                viewModel = searchViewModel,
                onMovieClick = { movieId ->
                    navController.navigate("movieDetails/$movieId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = "movieDetails/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: -1
            MovieDetailsScreen(
                viewModel = movieDetailsViewModel,
                movieId = movieId,
                onBackClick = { navController.popBackStack() })
        }
    }
}