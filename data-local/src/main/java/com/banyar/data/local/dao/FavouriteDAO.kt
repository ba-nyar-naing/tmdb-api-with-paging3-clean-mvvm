package com.banyar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banyar.data.local.model.FavouriteEntity

@Dao
interface FavouriteDAO {

    @Query("DELETE FROM favourites")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM favourites WHERE id =:id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * FROM favourites ORDER BY updated_at DESC")
    suspend fun getAll(): List<FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteEntity: FavouriteEntity): Long
}