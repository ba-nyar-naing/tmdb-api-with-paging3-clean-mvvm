package com.banyar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.banyar.data.local.dao.PaginatedMovieDAO
import com.banyar.data.local.dao.RemoteKeyDAO
import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.data.local.model.RemoteKeyEntity

@Database(
    entities = [PaginatedMovieEntity::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun popularMovieDAO(): PaginatedMovieDAO

    abstract fun remoteKeyDao(): RemoteKeyDAO
}