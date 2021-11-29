package com.banyar.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.MovieSourceType

@Dao
interface PaginatedMovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paginatedMovieEntity: PaginatedMovieEntity): Long

    @Query("SELECT * FROM paginated_movies WHERE category =:category ORDER BY paging_id ASC")
    fun pagingSource(category: String): PagingSource<Int, MovieDetails>

    @Query("SELECT * FROM paginated_movies WHERE category =:category ORDER BY updated_at DESC")
    fun pagingSourceFavourite(category: String = MovieSourceType.FAVOURITE.toString()): PagingSource<Int, MovieDetails>

    @Query("DELETE FROM paginated_movies WHERE category =:category")
    suspend fun deleteAll(category: String)

    @Query("DELETE FROM paginated_movies WHERE movie_id =:id and category =:category")
    suspend fun delete(id: Int, category: String): Int

    @Query("SELECT * FROM paginated_movies WHERE movie_id =:id and category =:category")
    suspend fun get(id: Int, category: String): MovieDetails?
}