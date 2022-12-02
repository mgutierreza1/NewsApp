package com.example.newsapp.ui.main

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beust.klaxon.Klaxon
import com.example.newsapp.techCrunchAdapter
import com.example.newsapp.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

data class Noticia(val author: String, val title: String, val description: String, val urlToImage: String)
data class  Articulos(val articles: ArrayList<Noticia>)

class FirstFragment: Fragment() {
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    // Create OkHttp Client
    private var client: OkHttpClient = OkHttpClient();

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)!!
        swipeRefreshLayout = view.findViewById(R.id.container)
        setRecyclerView(view)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            setRecyclerView(view)
        }
        return view
    }

    fun setRecyclerView(view: View) {
        val articles = setNews()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = techCrunchAdapter(articles)
    }

    fun setNews(): Articulos? {
        var articulos: Articulos? = null
        val result = getNews()
        try {
            // Parse result string JSON to data class
            articulos = result?.let { it1 -> Klaxon().parse<Articulos>(it1) }
        }
        catch(err:Error) {
            print("Error when parsing JSON: "+err.localizedMessage)
        }
        return articulos
    }

    fun getNews(): String? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var result: String? = null
        try {
            // Create URL
            val url = URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=76d5b4ecd3854737936a3c73ae75973c")
            // Build request
            val request = Request.Builder().url(url).build()
            // Execute request
            val response = client.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            print("Error when executing get request: "+err.localizedMessage)
        }
        return result
    }
}
