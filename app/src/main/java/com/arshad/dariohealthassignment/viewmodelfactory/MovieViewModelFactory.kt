package com.arshad.dariohealthassignment.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.db.MovieRepository
import com.arshad.dariohealthassignment.viewmodel.MovieViewModel
import java.lang.IllegalArgumentException

class MovieViewModelFactory (private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}