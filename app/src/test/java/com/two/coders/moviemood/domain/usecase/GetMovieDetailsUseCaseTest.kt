package com.two.coders.moviemood.domain.usecase

import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.utils.AppResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest {

    private val repository = mock(MoviesRepository::class.java)
    private val useCase = GetMovieDetailsUseCase(repository)

    @Test
    fun `invoke should return movie details on success`() = runTest {
        val movie = Movie(id = 123, name = "Test Movie")
        `when`(repository.getMovieDetails(123)).thenReturn(AppResult.Success(movie))

        val result = useCase.invoke(123)

        assertEquals(AppResult.Success(movie), result)
    }

    @Test
    fun `invoke should return error on failure`() = runTest {
        `when`(repository.getMovieDetails(123)).thenReturn(AppResult.Error("Error"))

        val result = useCase.invoke(123)

        assertEquals(AppResult.Error("Error"), result)
    }
}