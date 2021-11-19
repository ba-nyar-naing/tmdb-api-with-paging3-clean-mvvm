package com.banyar.presentation.di

import com.banyar.domain.repository.LocalFavouritesRepository
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
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
        repository: LocalFavouritesRepository
    ): DeleteFavouriteUC = DeleteFavouriteUCImpl(repository)

    @Provides
    @Singleton
    fun provideInsertFavouriteUseCase(
        repository: LocalFavouritesRepository
    ): InsertFavouriteUC = InsertFavouriteUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetFavouriteStatUseCase(
        repository: LocalFavouritesRepository
    ): GetFavouriteStatUC = GetFavouriteStatUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetFavouritesUseCase(
        repository: LocalFavouritesRepository
    ): GetFavouritesUC = GetFavouritesUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetDetailsUseCase(
        repository: RemoteMoviesRepository
    ): GetDetailsUC = GetDetailsUCImpl(repository)

    @Provides
    @Singleton
    fun provideGetPopularMediatorUseCase(
        remoteMoviesRepository: RemoteMoviesRepository,
        localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
        localRemoteKeyRepository: LocalRemoteKeyRepository,
    ): GetPopularMediatorUC =
        GetPopularMediatorUCImpl(
            remoteMoviesRepository,
            localPaginatedMoviesRepository,
            localRemoteKeyRepository
        )

    @Provides
    @Singleton
    fun provideGetUpcomingMediatorUseCase(
        remoteMoviesRepository: RemoteMoviesRepository,
        localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
        localRemoteKeyRepository: LocalRemoteKeyRepository,
    ): GetUpcomingMediatorUC =
        GetUpcomingMediatorUCImpl(
            remoteMoviesRepository,
            localPaginatedMoviesRepository,
            localRemoteKeyRepository
        )

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(
        popularMediatorUC: GetPopularMediatorUC,
        upcomingMediatorUC: GetUpcomingMediatorUC,
    ): GetCategoriesUC = GetCategoriesUCImpl(
        popularMediatorUC, upcomingMediatorUC
    )

}