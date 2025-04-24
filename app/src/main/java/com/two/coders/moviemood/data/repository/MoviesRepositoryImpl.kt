package com.two.coders.moviemood.data.repository

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun getMovies(): ArrayList<Movie> {
        return arrayListOf(
            Movie(0, "Movie 1", "/movie1.jpg"),
            Movie(1, "Movie 2", "/movie2.jpg"),
            Movie(2, "Movie 3", "/movie3.jpg"),
            Movie(3, "Movie 4", "/movie4.jpg"),
            Movie(4, "Movie 5", "/movie5.jpg"),
            Movie(5, "Movie 6", "/movie6.jpg"),
            Movie(6, "Movie 7", "/movie7.jpg"),
            Movie(7, "Movie 8", "/movie8.jpg"),
            Movie(8, "Movie 9", "/movie9.jpg")
        )
    }
}