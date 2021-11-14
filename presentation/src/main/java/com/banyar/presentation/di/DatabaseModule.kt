package com.banyar.presentation.di

import android.content.Context
import androidx.room.Room
import com.banyar.data.local.TMDBDatabase
import com.banyar.data.local.dao.FavouriteDAO
import com.banyar.data.local.dao.MovieDAO
import com.banyar.data.local.dao.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TMDBDatabase = Room
        .databaseBuilder(context, TMDBDatabase::class.java, "tmdb")
        .build()

    @Provides
    @Singleton
    fun provideFavouriteDao(db: TMDBDatabase): FavouriteDAO = db.favouriteDao()

    @Provides
    @Singleton
    fun provideMovieDao(db: TMDBDatabase): MovieDAO = db.movieDao()

    @Provides
    @Singleton
    fun provideRemoteKeyDao(db: TMDBDatabase): RemoteKeyDao = db.remoteKeyDao()
}