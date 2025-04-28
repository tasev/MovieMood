package com.two.coders.moviemood.presentation.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.two.coders.moviemood.domain.model.AuthorDetails
import com.two.coders.moviemood.domain.model.Movie
import com.two.coders.moviemood.domain.model.MovieReview
import com.two.coders.moviemood.domain.usecase.GetMovieDetailsUseCase
import com.two.coders.moviemood.domain.usecase.GetMovieReviewsUseCase
import com.two.coders.moviemood.utils.AppResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getMovieDetailsUseCase = mock(GetMovieDetailsUseCase::class.java)
    private val getMovieReviewsUseCase = mock(GetMovieReviewsUseCase::class.java)
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        viewModel = MovieDetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            getMovieReviewsUseCase = getMovieReviewsUseCase,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `fetchMovieDetail should update state with movie details`() = testScope.runTest {
        val movie = Movie(id = 123, name = "Test Movie")
        `when`(getMovieDetailsUseCase.invoke(123)).thenReturn(AppResult.Success(movie))
        `when`(getMovieReviewsUseCase.invoke(123)).thenReturn(AppResult.Success(arrayListOf()))

        viewModel.fetchMovieDetail(123)
        advanceUntilIdle()

        assertEquals(movie, viewModel.state.value.movie)
    }

    @Test
    fun `fetchMovieDetail should update state with movie reviews`() = testScope.runTest {
        val reviews = arrayListOf(
            MovieReview(
                id = "1",
                content = "Great movie!",
                author = "John Doe",
                authorDetails = AuthorDetails(
                    name = "John Doe", username = "johndoe", avatarPath = null, rating = "5"
                ),
                updatedAt = "2023-10-01"
            )
        )
        `when`(getMovieDetailsUseCase.invoke(123)).thenReturn(AppResult.Success(Movie(id = 123)))
        `when`(getMovieReviewsUseCase.invoke(123)).thenReturn(AppResult.Success(reviews))

        viewModel.fetchMovieDetail(123)
        advanceUntilIdle()

        assertEquals(reviews, viewModel.state.value.movieReviews)
    }

    @Test
    fun `fetchMovieDetail should handle movie details error gracefully`() = testScope.runTest {
        `when`(getMovieDetailsUseCase.invoke(123)).thenReturn(AppResult.Error("Error"))
        `when`(getMovieReviewsUseCase.invoke(123)).thenReturn(AppResult.Success(arrayListOf()))

        viewModel.fetchMovieDetail(123)
        advanceUntilIdle()

        assertEquals("Error", viewModel.state.value.error)
    }

}