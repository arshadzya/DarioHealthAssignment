package com.arshad.dariohealthassignment.viewmodel

import androidx.lifecycle.LiveData
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.db.Movie
import com.anushka.roomdemo.db.MovieRepository
import com.arshad.dariohealthassignment.Event
import kotlinx.coroutines.launch

class MovieViewModel (private val repository: MovieRepository) : ViewModel() {



    val movieTitle = MutableLiveData<String>()
    val year = MutableLiveData<String>()
    val imdbID = MutableLiveData<String>()
    val type = MutableLiveData<String>()



    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage



    fun insert(subscriber: Movie) = viewModelScope.launch {
        val newRowId = repository.insert(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Movie detail saved Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }



    fun delete(subscriber: Movie) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(subscriber)
        if (noOfRowsDeleted > 0) {
            movieTitle.value = ""
            year.value = ""
            imdbID.value = ""
            type.value = ""

            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }
}



