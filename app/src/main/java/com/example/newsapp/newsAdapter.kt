package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.serialization.Serializable

@Serializable
data class Noticia(val author: String = "Anonimo",
                   val title: String,
                   val description: String,
                   val url: String,
                   val urlToImage: String = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fsidecafsrl.com%2Fcategory%2Fimpuestos-nacionales%2F&psig=AOvVaw3uhBYTTdlVxymKpiCsREMj&ust=1670333404131000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCNjcjP_K4vsCFQAAAAAdAAAAABAD")
@Serializable
data class Articulos(val articles: ArrayList<Noticia>)

class NewsAdapter(articles: Articulos?, context: Context?): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var articulos: Articulos? = articles
    var mContext: Context? = context
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageNew: ImageView? = null
        var titleTV: TextView? = null
        var authorTV: TextView? = null
        var descriptionTV: TextView? = null
        var btnCMore: Button? = null
        var btnShare: Button? = null
        init {
            // Define click listener for the ViewHolder's View.
            imageNew        = view.findViewById(R.id.imageView)
            titleTV         = view.findViewById(R.id.news_title)
            authorTV        = view.findViewById(R.id.news_author)
            descriptionTV   = view.findViewById(R.id.news_description)
            btnCMore        = view.findViewById(R.id.btn_cmore)
            btnShare        = view.findViewById(R.id.btn_share)
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
            holder.authorTV!!.text = R.string.anonymous.toString()
        } else {
            holder.authorTV!!.text = data.author
        }
        holder.descriptionTV!!.text = data.description

        val URL = data.url
        val intentView = Intent(Intent.ACTION_VIEW)
        intentView.data = Uri.parse(URL)
        // Buttons actions
        holder.btnCMore!!.setOnClickListener{ view ->
            mContext!!.startActivity(intentView)
        }

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, URL)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        holder.btnShare!!.setOnClickListener{ view ->
            mContext!!.startActivity(shareIntent)
        }
    }

    private fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }

    override fun getItemCount(): Int {
        return articulos!!.articles.size
    }
}