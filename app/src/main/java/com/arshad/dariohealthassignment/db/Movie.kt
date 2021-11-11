package com.anushka.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_data_table")
data class Movie (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id : Int,

    @ColumnInfo(name = "Title")
    val title : String,

    @ColumnInfo(name = "Year")
    val year : String,

    @ColumnInfo(name = "imdbID")
    val imdbID : String,

    @ColumnInfo(name = "Type")
    val type : String

)