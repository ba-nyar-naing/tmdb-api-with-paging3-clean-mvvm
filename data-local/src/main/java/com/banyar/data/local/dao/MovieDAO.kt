package com.banyar.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banyar.domain.model.MovieDetails

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(users: List<MovieEntity>)
    suspend fun insert(movieDetails: MovieDetails): Long

    @Query("SELECT * FROM movies ORDER BY pagingId ASC")
//    fun pagingSource(title: String): PagingSource<Int, MovieEntity>
    fun pagingSource(): PagingSource<Int, MovieDetails>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}