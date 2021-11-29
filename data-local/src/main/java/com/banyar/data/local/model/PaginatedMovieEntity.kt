package com.banyar.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paginated_movies")
data class PaginatedMovieEntity(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "paging_id") val pagingId: Int = 0,
)