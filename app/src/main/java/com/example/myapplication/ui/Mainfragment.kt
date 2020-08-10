package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch

class Mainfragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = layoutInflater.inflate(R.layout.fragment_main, container, false)
        val btn = v.findViewById<Button>(R.id.button9)
        btn.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.container,Lightfragment())
                .commit()
        }
        return v
    }
fun getLight(){
    lifecycleScope.launch{
        val light = WebClient.getLightLux()
        Log.d("Mainfragment","${light.levelMax}"+" ${light.levelMin}")
    }
}
}

