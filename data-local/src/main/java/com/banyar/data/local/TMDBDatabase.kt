package com.banyar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.banyar.data.local.dao.FavouriteDAO
import com.banyar.data.local.model.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 1, exportSchema = false)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDAO
}