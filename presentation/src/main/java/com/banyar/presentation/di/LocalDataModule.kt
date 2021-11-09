package com.banyar.presentation.di

import com.banyar.data.local.repository.FavouriteRepositoryImpl
import com.banyar.domain.repository.FavouriteRepository
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
        repositoryImpl: FavouriteRepositoryImpl
    ): FavouriteRepository = repositoryImpl
}