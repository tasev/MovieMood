package com.two.coders.moviemood.data.repository

import com.two.coders.moviemood.data.remote.api.MoviesApi
import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.model.MovieReview
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesRepository {

    // Fetches a list of movies for the given page
    override suspend fun getMovies(currentPage: Int): AppResult<ArrayList<Movie>> {
        return try {
            val response = api.getMovies(currentPage = currentPage)
            val movies = ArrayList(response.results.map { it.toDomain() }) // Maps DTOs to domain models
            AppResult.Success(movies)
        } catch (e: HttpException) {
            AppResult.Error("Network error: ${e.code()} ${e.message()}")
        } catch (_: IOException) {
            AppResult.Error("Check your internet connection.")
        } catch (e: Exception) {
            AppResult.Error("Something went wrong: ${e.localizedMessage}")
        }
    }

    // Fetches details of a specific movie by its ID
    override suspend fun getMovieDetails(movieId: Int): AppResult<Movie> {
        return try {
            val response = api.getMovieDetails(movieId = movieId)
            AppResult.Success(response.toDomain()) // Converts DTO to domain model
        } catch (e: HttpException) {
            AppResult.Error("Network error: ${e.code()} ${e.message()}")
        } catch (_: IOException) {
            AppResult.Error("Check your internet connection.")
        } catch (e: Exception) {
            AppResult.Error("Something went wrong: ${e.localizedMessage}")
        }
    }

    // Fetches reviews for a specific movie
    override suspend fun getMovieReviews(movieId: Int): AppResult<ArrayList<MovieReview>> {
        return try {
            val response = api.getMovieReviews(movieId = movieId)
            AppResult.Success(ArrayList(response.results.map { it.toDomain() }))
        } catch (e: HttpException) {
            AppResult.Error("Network error: ${e.code()} ${e.message()}")
        } catch (_: IOException) {
            AppResult.Error("Check your internet connection.")
        } catch (e: Exception) {
            AppResult.Error("Something went wrong: ${e.localizedMessage}")
        }
    }

    // Searches for movies based on a query string
    override suspend fun searchMovies(query: String): AppResult<ArrayList<Movie>> {
        return try {
            val response = api.searchMovies(query = query)
            val movies = ArrayList(response.results.map { it.toDomain() })
            AppResult.Success(movies)
        } catch (e: HttpException) {
            AppResult.Error("Network error: ${e.code()} ${e.message()}")
        } catch (_: IOException) {
            AppResult.Error("Check your internet connection.")
        } catch (e: Exception) {
            AppResult.Error("Something went wrong: ${e.localizedMessage}")
        }
    }
}