package com.banyar.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies")
data class PopularMovieEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "paging_id") val pagingId: Int = 0,
)