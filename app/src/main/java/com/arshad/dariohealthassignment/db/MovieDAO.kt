package com.anushka.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDAO {

    @Insert
    suspend fun insertMovie(subscriber: Movie): Long

    @Update
    suspend fun updateMovie(subscriber: Movie): Int

    @Delete
    suspend fun deleteMovie(subscriber: Movie): Int

    @Query("DELETE FROM movie_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM movie_data_table")
    fun getAllMovies():LiveData<List<Movie>>
}