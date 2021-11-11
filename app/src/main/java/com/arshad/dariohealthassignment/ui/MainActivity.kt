package com.arshad.dariohealthassignment.ui

import Search
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arshad.dariohealthassignment.R
import com.arshad.dariohealthassignment.adaptor.MainActivityReposAdaptor
import com.arshad.dariohealthassignment.cache.ImageCache
import com.arshad.dariohealthassignment.data.TimeUtility
import com.arshad.dariohealthassignment.databinding.ActivityMainBinding
import com.arshad.dariohealthassignment.viewmodel.MainActivityViewModel
import com.arshad.dariohealthassignment.viewmodelfactory.MainActivityViewModelFactory
import com.google.gson.Gson
import com.skymet.doordrishti.view.Status
import com.skymet.doordrishti.view.data.api.ApiHelper
import com.skymet.doordrishti.view.data.api.RetrofitBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityReposAdaptor: MainActivityReposAdaptor
    private var arrayList = ArrayList<Search>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarId.imageView.setOnClickListener {
            onBackPressed()
        }

        ImageCache.init(this)

        val mLayoutManagerNews: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainactivityRecycleview.layoutManager = mLayoutManagerNews
        binding.mainactivityRecycleview.setHasFixedSize(true)
        setupViewModel()
        binding.toolbarId.headerSearchEdittext.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {


            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

                    if(count>2){
                        if (this@MainActivity::mainActivityReposAdaptor.isInitialized) {
                            arrayList!!.clear()
                            mainActivityReposAdaptor!!.notifyDataSetChanged()
                        }
                        setupObservers("9278e7bc", s.toString())
                    }else{
                        if (this@MainActivity::mainActivityReposAdaptor.isInitialized) {
                            arrayList!!.clear()
                            mainActivityReposAdaptor!!.notifyDataSetChanged()
                        }
                    }
            }
        })







    }

    private fun setupViewModel() {
        mainActivityViewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)
    }

    private fun setupObservers(authKey: String, searchKey: String) {
        binding.shimmerLoader.visibility = View.VISIBLE
        mainActivityViewModel.getRepositories(authKey, searchKey).observe(this, {
            it?.let { resource ->


                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.shimmerLoader.visibility = View.GONE
                        binding.mainactivityRecycleview.visibility = View.VISIBLE



                        resource.data.apply{


                            if(resource.data!!.Response){
                                arrayList.addAll(resource.data.Search)
                                mainActivityReposAdaptor =
                                    MainActivityReposAdaptor(arrayList, this@MainActivity)
                                binding.mainactivityRecycleview.adapter = mainActivityReposAdaptor
                            }

                        }


                    }
                    Status.ERROR -> {
                          System.out.println("Registration error"+resource.message)
                        binding.shimmerLoader.visibility = View.GONE
                        binding.mainactivityRecycleview.visibility = View.GONE


                     }
                    Status.LOADING -> {
                        binding.shimmerLoader.visibility = View.VISIBLE
                        binding.mainactivityRecycleview.visibility = View.GONE
                    }
                }
            }
        })
    }
}