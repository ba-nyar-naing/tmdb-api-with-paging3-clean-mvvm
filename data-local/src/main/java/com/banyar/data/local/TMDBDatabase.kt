package com.banyar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.banyar.data.local.converter.MovieConverter
import com.banyar.data.local.dao.FavouriteDAO
import com.banyar.data.local.dao.PaginatedMovieDAO
import com.banyar.data.local.dao.RemoteKeyDAO
import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.data.local.model.RemoteKeyEntity

@Database(
    entities = [FavouriteEntity::class, PaginatedMovieEntity::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MovieConverter::class)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDAO

    abstract fun popularMovieDAO(): PaginatedMovieDAO

    abstract fun remoteKeyDao(): RemoteKeyDAO
}