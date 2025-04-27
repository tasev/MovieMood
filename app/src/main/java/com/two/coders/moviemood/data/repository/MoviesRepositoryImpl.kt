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

    override suspend fun getMovies(currentPage: Int): AppResult<ArrayList<Movie>> {
        return try {
            val response = api.getMovies(currentPage = currentPage)
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

    override suspend fun getMovieDetails(movieId: Int): AppResult<Movie> {
        return try {
            val response = api.getMovieDetails(movieId = movieId)
            AppResult.Success(response.toDomain())
        } catch (e: HttpException) {
            AppResult.Error("Network error: ${e.code()} ${e.message()}")
        } catch (_: IOException) {
            AppResult.Error("Check your internet connection.")
        } catch (e: Exception) {
            AppResult.Error("Something went wrong: ${e.localizedMessage}")
        }
    }

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