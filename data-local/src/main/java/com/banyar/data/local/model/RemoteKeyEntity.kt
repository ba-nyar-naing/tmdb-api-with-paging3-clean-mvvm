package com.banyar.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    val category: String,
    val movieId: Int?,
    val prevKey: Int?,
    val nextKey: Int?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)