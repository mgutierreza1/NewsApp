package com.example.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.R

class SecondFragment: Fragment()  {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.favorites_fragment, container, false)!!
}