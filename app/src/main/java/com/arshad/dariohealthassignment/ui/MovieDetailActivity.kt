package com.arshad.dariohealthassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.ImageLoader
import com.anushka.roomdemo.db.Movie
import com.anushka.roomdemo.db.MovieDatabase
import com.anushka.roomdemo.db.MovieRepository
import com.arshad.dariohealthassignment.R
import com.arshad.dariohealthassignment.cache.ImageCache
import com.arshad.dariohealthassignment.databinding.ActivityMoviewDetailBinding
import com.arshad.dariohealthassignment.databinding.ActivityOpeningBinding
import com.arshad.dariohealthassignment.viewmodel.MovieViewModel
import com.arshad.dariohealthassignment.viewmodelfactory.MovieViewModelFactory

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviewDetailBinding
    private val mImageLoader: ImageLoader = ImageCache.get().imageLoader
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moview_detail)

        binding = ActivityMoviewDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val poster:String = intent.getStringExtra("movie_poster").toString()
        val title:String = intent.getStringExtra("movie_title").toString()
        val type:String = intent.getStringExtra("movie_type").toString()
        val imdbid:String = intent.getStringExtra("movie_imdbid").toString()
        val year:String = intent.getStringExtra("movie_year").toString()

       /* val dao = MovieDatabase.getInstance(application).movieDAO
        val repository = MovieRepository(dao)
        val factory = MovieViewModelFactory(repository)*/

       /* movieViewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)

        movieViewModel.imdbID.value = imdbid
        movieViewModel.movieTitle.value = title
        movieViewModel.type.value = type
        movieViewModel.year.value = year*/

        var movie = Movie(0,title, year,imdbid, type)

        binding.imageView6.setImageUrl(
            poster,
            mImageLoader
        )

        binding.textView6.setText(title)

        binding.imageView7.setOnClickListener {

            binding.imageView7.setImageResource(R.drawable.ic_star_7207)
           // movieViewModel.insert(movie)
        }

        /*movieViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })*/
    }
}