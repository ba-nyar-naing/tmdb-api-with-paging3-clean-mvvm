package com.banyar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.banyar.data.local.converter.MovieConverter
import com.banyar.data.local.dao.FavouriteDAO
import com.banyar.data.local.dao.PopularMovieDAO
import com.banyar.data.local.dao.RemoteKeyDAO
import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.PopularMovieEntity
import com.banyar.data.local.model.RemoteKeyEntity

@Database(
    entities = [FavouriteEntity::class, PopularMovieEntity::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MovieConverter::class)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDAO

    abstract fun popularMovieDAO(): PopularMovieDAO

    abstract fun remoteKeyDao(): RemoteKeyDAO
}