package com.example.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.R

class ThirdFragment: Fragment()  {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.saves_fragment, container, false)!!
}