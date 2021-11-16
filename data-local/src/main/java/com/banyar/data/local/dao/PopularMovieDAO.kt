package com.banyar.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banyar.data.local.model.PopularMovieEntity
import com.banyar.domain.model.MovieDetails

@Dao
interface PopularMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(popularMovieEntity: PopularMovieEntity): Long

    @Query("SELECT * FROM popular_movies ORDER BY paging_id ASC")
    fun pagingSource(): PagingSource<Int, MovieDetails>

    @Query("DELETE FROM popular_movies")
    suspend fun deleteAll()
}