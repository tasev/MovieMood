package com.two.coders.moviemood.di

import com.two.coders.moviemood.data.repository.MoviesRepositoryImpl
import com.two.coders.moviemood.domain.repository.MoviesRepository
import com.two.coders.moviemood.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(): MoviesRepository = MoviesRepositoryImpl()

    @Provides
    fun provideGetMoviesUseCase(repository: MoviesRepository): GetMoviesUseCase =
        GetMoviesUseCase(repository)
}