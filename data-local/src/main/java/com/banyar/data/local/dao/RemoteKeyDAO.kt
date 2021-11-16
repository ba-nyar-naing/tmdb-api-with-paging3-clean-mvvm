package com.banyar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banyar.data.local.model.RemoteKeyEntity

@Dao
interface RemoteKeyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: RemoteKeyEntity): Long

    @Query("SELECT * FROM remote_keys WHERE movieId = :movieId")
    suspend fun getRemoteKey(movieId: Int): RemoteKeyEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()
}