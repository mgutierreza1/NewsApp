package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.ui.main.Articulos
import com.squareup.picasso.Picasso

class NewsAdapter(articles: Articulos?): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var articulos: Articulos? = articles
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageNew: ImageView? = null
        var titleTV: TextView? = null
        var authorTV: TextView? = null
        var descriptionTV: TextView? = null
        init {
            // Define click listener for the ViewHolder's View.
            imageNew        = view.findViewById(R.id.imageView)
            titleTV         = view.findViewById(R.id.news_title)
            authorTV        = view.findViewById(R.id.news_author)
            descriptionTV   = view.findViewById(R.id.news_description)
        }
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = articulos!!.articles[position]
        holder.imageNew!!.loadUrl(data.urlToImage)
        holder.titleTV!!.text = data.title
        if(data.author.equals(null)) {
            holder.authorTV!!.text = "Anónimo"
        } else {
            holder.authorTV!!.text = data.author
        }
        holder.descriptionTV!!.text = data.description
    }

    private fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }

    override fun getItemCount(): Int {
        return articulos!!.articles.size
    }
}