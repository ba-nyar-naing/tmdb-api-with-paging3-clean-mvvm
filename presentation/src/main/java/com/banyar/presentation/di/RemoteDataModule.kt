package com.banyar.presentation.di

import com.banyar.data.remote.repository.MovieListingRepositoryImpl
import com.banyar.domain.repository.MovieListingRepository
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
        repositoryImpl: MovieListingRepositoryImpl
    ): MovieListingRepository = repositoryImpl
}