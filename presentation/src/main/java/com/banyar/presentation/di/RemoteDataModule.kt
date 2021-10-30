package com.banyar.presentation.di

import com.banyar.data.remote.repository.MoviesRepositoryImpl
import com.banyar.domain.repository.MoviesRepository
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
    fun provideListingRepo(
        repositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository = repositoryImpl
}