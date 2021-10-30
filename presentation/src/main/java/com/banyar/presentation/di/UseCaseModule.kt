package com.banyar.presentation.di

import com.banyar.domain.repository.MoviesRepository
import com.banyar.domain.usecase.GetPopularBaseUC
import com.banyar.domain.usecase.GetPopularUC
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
    fun provideGetPopularUC(
        repository: MoviesRepository
    ): GetPopularBaseUC = GetPopularUC(repository)
}
