package com.arshad.dariohealthassignment.adaptor


import MovieListModel
import Search
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.arshad.dariohealthassignment.R
import com.arshad.dariohealthassignment.cache.ImageCache
import com.arshad.dariohealthassignment.ui.MovieDetailActivity


class MainActivityReposAdaptor(private val repoList: ArrayList<Search>?, val mContext: Context) : RecyclerView.Adapter<MainActivityReposAdaptor.ViewHolder>(){

    private val mImageLoader: ImageLoader = ImageCache.get().imageLoader



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val adapterView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_activity_recycleview_item, parent, false)
        return ViewHolder(adapterView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.mImageViewThumb.setImageUrl(
            repoList!!.get(position).Poster,
            mImageLoader
        )

        holder.mTextViewOrgName.text = repoList.get(position).Type
        holder.mTextViewPubName.text = repoList.get(position).Title
        holder.mTextViewDescription.text = repoList.get(position).Year


        holder.mMainContainer.setOnClickListener {
            val movieDetailIntent = Intent(mContext, MovieDetailActivity::class.java)
            val b = Bundle()
            b.putString("movie_title", repoList.get(position).Title)
            b.putString("movie_type", repoList.get(position).Type)
            b.putString("movie_poster", repoList.get(position).Poster)
            b.putString("movie_imdbid", repoList.get(position).imdbID)
            b.putString("movie_year", repoList.get(position).Year)
            movieDetailIntent.putExtras(b)
            mContext.startActivity(movieDetailIntent)
        }


    }

    override fun getItemCount(): Int = repoList?.size!!


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mImageViewThumb: NetworkImageView
        var mTextViewOrgName: TextView
        var mTextViewPubName: TextView
        var mTextViewDescription: TextView
        var mMainContainer: ConstraintLayout


        init {
            mImageViewThumb = view.findViewById(R.id.thumbnail)
            mTextViewOrgName = view.findViewById(R.id.org_name_textview)
            mTextViewPubName = view.findViewById(R.id.pub_name_textview)
            mTextViewDescription = view.findViewById(R.id.project_decription_textview)
            mMainContainer = view.findViewById(R.id.main_item_container)

        }


    }

}