package com.banyar.presentation.di

import com.banyar.data.local.repository.LocalFavouritesRepositoryImpl
import com.banyar.data.local.repository.PaginatedMoviesRepositoryImpl
import com.banyar.data.local.repository.LocalRemoteKeyRepositoryImpl
import com.banyar.domain.repository.LocalFavouritesRepository
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalDataModule {

    @Provides
    @Singleton
    fun provideFavouriteRepo(
        repositoryImpl: LocalFavouritesRepositoryImpl
    ): LocalFavouritesRepository = repositoryImpl

    @Provides
    @Singleton
    fun providePopularMovieRepo(
        repositoryImpl: PaginatedMoviesRepositoryImpl
    ): LocalPaginatedMoviesRepository = repositoryImpl

    @Provides
    @Singleton
    fun provideRemoteKeyRepo(
        repositoryImpl: LocalRemoteKeyRepositoryImpl
    ): LocalRemoteKeyRepository = repositoryImpl
}