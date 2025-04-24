package com.two.coders.moviemood.data.repository

import com.two.coders.moviemood.data.remote.api.MoviesApi
import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesRepository {

    override suspend fun getMovies(): Result<ArrayList<Movie>> {
        return try {
            val response = api.getMovies()
            val movies = ArrayList(response.results.map { it.toDomain() })
            Result.Success(movies)
        } catch (e: HttpException) {
            Result.Error("Network error: ${e.code()} ${e.message()}")
        } catch (_: IOException) {
            Result.Error("Check your internet connection.")
        } catch (e: Exception) {
            Result.Error("Something went wrong: ${e.localizedMessage}")
        }
    }
}