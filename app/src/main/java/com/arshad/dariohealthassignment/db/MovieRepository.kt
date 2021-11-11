package com.anushka.roomdemo.db

class MovieRepository(private val dao : MovieDAO) {

    val subscribers = dao.getAllMovies()

    suspend fun insert(subscriber: Movie): Long{
        return dao.insertMovie(subscriber)
    }

    suspend fun update(subscriber: Movie): Int{
        return dao.updateMovie(subscriber)
    }

    suspend fun delete(subscriber: Movie): Int {
        return dao.deleteMovie(subscriber)
    }


}