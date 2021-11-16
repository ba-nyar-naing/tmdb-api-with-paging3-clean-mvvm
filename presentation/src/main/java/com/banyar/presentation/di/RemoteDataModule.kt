package com.banyar.presentation.di

import com.banyar.data.remote.repository.RemoteMoviesRepositoryImpl
import com.banyar.domain.repository.RemoteMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteDataModule {

    @Provides
    @Singleton
    fun provideMoviesRepo(
        repositoryImpl: RemoteMoviesRepositoryImpl
    ): RemoteMoviesRepository = repositoryImpl
}