package com.banyar.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.domain.model.MovieDetails

@Dao
interface PaginatedMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paginatedMovieEntity: PaginatedMovieEntity): Long

    @Query("SELECT * FROM paginated_movies WHERE category =:category ORDER BY paging_id ASC")
    fun pagingSource(category: String): PagingSource<Int, MovieDetails>

    @Query("DELETE FROM paginated_movies WHERE category =:category")
    suspend fun deleteAll(category: String)
}