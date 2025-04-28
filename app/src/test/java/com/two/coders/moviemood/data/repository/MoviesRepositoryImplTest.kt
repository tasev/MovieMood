package com.two.coders.moviemood.data.repository

import com.two.coders.moviemood.data.remote.api.MoviesApi
import com.two.coders.moviemood.data.remote.dto.MovieResponse
import com.two.coders.moviemood.data.repository.MoviesRepositoryImpl
import com.two.coders.moviemood.utils.AppResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals

class MoviesRepositoryImplTest {

    private val api = mock(MoviesApi::class.java)
    private val repository = MoviesRepositoryImpl(api)

    @Test
    fun `getMovieDetails should return success when API call is successful`() = runBlocking {
        val movieResponse = MovieResponse(id = 123, name = "Test Movie")
        `when`(api.getMovieDetails(123)).thenReturn(movieResponse)

        val result = repository.getMovieDetails(123)

        assertTrue(result is AppResult.Success)
        assertEquals("Test Movie", (result as AppResult.Success).data.name)
    }

    @Test
    fun `getMovieDetails should return error when API call fails`() = runBlocking {
        `when`(api.getMovieDetails(123)).thenThrow(RuntimeException("API error"))

        val result = repository.getMovieDetails(123)

        assertTrue(result is AppResult.Error)
    }
}