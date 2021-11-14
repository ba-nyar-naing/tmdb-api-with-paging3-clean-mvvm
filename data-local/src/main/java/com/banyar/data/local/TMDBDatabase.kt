package com.banyar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.banyar.data.local.dao.FavouriteDAO
import com.banyar.data.local.dao.MovieDAO
import com.banyar.data.local.dao.RemoteKeyDao
import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.MovieDetails

@Database(
    entities = [FavouriteEntity::class, MovieDetails::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDAO

    abstract fun movieDao(): MovieDAO

    abstract fun remoteKeyDao(): RemoteKeyDao
}