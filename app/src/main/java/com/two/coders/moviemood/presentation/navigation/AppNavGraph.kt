package com.two.coders.moviemood.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.two.coders.moviemood.presentation.ui.home.HomeScreen
import com.two.coders.moviemood.presentation.ui.home.HomeViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel = homeViewModel)
        }
    }
}