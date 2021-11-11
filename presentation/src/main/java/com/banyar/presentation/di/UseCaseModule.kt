package com.banyar.presentation.di

import com.banyar.domain.repository.FavouriteRepository
import com.banyar.domain.repository.MoviesRepository
import com.banyar.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Provides
    @Singleton
    fun provideDeleteFavouriteUseCase(
        repository: FavouriteRepository
    ): DeleteFavouriteUC = DeleteFavouriteUCImpl(repository)

    @Provides
    @Singleton
    fun provideInsertFavouriteUseCase(
        repository: FavouriteRepository
    ): InsertFavouriteUC = InsertFavouriteUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetFavouriteStatUseCase(
        repository: FavouriteRepository
    ): GetFavouriteStatUC = GetFavouriteStatUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetFavouritesUseCase(
        repository: FavouriteRepository
    ): GetFavouritesUC = GetFavouritesUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetDetailsUseCase(
        repository: MoviesRepository
    ): GetDetailsUC = GetDetailsUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetPopularUseCase(
        repository: MoviesRepository
    ): GetPopularUC = GetPopularUCImpl(repository)
}