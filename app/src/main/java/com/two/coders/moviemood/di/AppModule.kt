package com.two.coders.moviemood.di

import com.two.coders.moviemood.data.remote.api.MoviesApi
import com.two.coders.moviemood.data.repository.MoviesRepositoryImpl
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
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

}