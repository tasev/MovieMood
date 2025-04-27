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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository = MoviesRepositoryImpl(api)

    @Provides
    fun provideGetMoviesUseCase(repository: MoviesRepository): GetMoviesUseCase =
        GetMoviesUseCase(repository)

    @Provides
    fun provideGetMovieDetailsUseCase(repository: MoviesRepository): GetMovieDetailsUseCase =
        GetMovieDetailsUseCase(repository)

    @Provides
    fun provideSearchMovieDetailsUseCase(repository: MoviesRepository): SearchMoviesUseCase =
        SearchMoviesUseCase(repository)

    @Provides
    fun provideMovieReviewsUseCase(repository: MoviesRepository): GetMovieReviewsUseCase =
        GetMovieReviewsUseCase(repository)

}