package com.two.coders.moviemood.di

import com.two.coders.moviemood.data.remote.api.MoviesApi
import com.two.coders.moviemood.data.repository.MoviesRepositoryImpl
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.domain.usecase.GetMovieDetailsUseCase
import com.two.coders.moviemood.domain.usecase.GetMovieReviewsUseCase
import com.two.coders.moviemood.domain.usecase.GetMoviesUseCase
import com.two.coders.moviemood.domain.usecase.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides dependencies for the application.
 * This module is installed in the SingletonComponent, meaning the provided
 * dependencies will have a singleton lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of [MoviesApi] for making network requests.
     * Configures an OkHttp client with logging for debugging purposes.
     */
    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Logs full response
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client) // attach the logging client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    /**
     * Provides an implementation of [MoviesRepository] using [MoviesRepositoryImpl].
     */
    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository = MoviesRepositoryImpl(api)

    /**
     * Provides a use case for fetching a list of movies.
     */
    @Provides
    fun provideGetMoviesUseCase(repository: MoviesRepository): GetMoviesUseCase =
        GetMoviesUseCase(repository)

    /**
     * Provides a use case for fetching details of a specific movie.
     */
    @Provides
    fun provideGetMovieDetailsUseCase(repository: MoviesRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repository)

    /**
     * Provides a use case for searching movies by a query string.
     */
    @Provides
    fun provideSearchMovieDetailsUseCase(repository: MoviesRepository): SearchMoviesUseCase =
        SearchMoviesUseCase(repository)

    /**
     * Provides a use case for fetching reviews of a specific movie.
     */
    @Provides
    fun provideMovieReviewsUseCase(repository: MoviesRepository): GetMovieReviewsUseCase =
        GetMovieReviewsUseCase(repository)
}