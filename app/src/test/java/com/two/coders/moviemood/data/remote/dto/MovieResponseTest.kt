package com.two.coders.moviemood.data.remote.dto

import org.junit.Test
import kotlin.test.assertEquals

class MovieResponseTest {

    @Test
    fun `toDomain should map MovieResponse to Movie correctly`() {
        val genreResponse = GenreResponse(id = 1, name = "Action")
        val movieResponse = MovieResponse(
            id = 123,
            name = "Test Movie",
            posterPath = "/test.jpg",
            overview = "Test overview",
            genres = listOf(genreResponse)
        )

        val movie = movieResponse.toDomain()

        assertEquals(123, movie.id)
        assertEquals("Test Movie", movie.name)
        assertEquals("https://image.tmdb.org/t/p/w500/test.jpg", movie.posterPath)
        assertEquals("Test overview", movie.overview)
        assertEquals(1, movie.genres.size)
        assertEquals("Action", movie.genres[0].name)
    }
}