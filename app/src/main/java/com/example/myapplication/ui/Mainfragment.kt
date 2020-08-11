package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.data.DataLight
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch

class Mainfragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = layoutInflater.inflate(R.layout.fragment_main, container, false)
        val btn = v.findViewById<Button>(R.id.button9)
        btn.setOnClickListener{
            getLight()
            accessPhoto()
            //setLight()
        }
        return v
    }
fun getLight(){
    lifecycleScope.launch{
        val light = WebClient.getLightLux()
        Log.d("Mainfragment","${light.state} ${light.level_max}"+" ${light.level_min}")
    }
}
    fun setLight(){
        lifecycleScope.launch{
            WebClient.setLightLux(DataLight(true,30,80))
        }
    }
    fun accessPhoto(){
        lifecycleScope.launch {
            val photo = WebClient.getAccessPhoto()
            Log.d("Mainfragment", "${photo.photo}")
        }
    }
    fun dooropen(){
        lifecycleScope.launch {
            WebClient.setDoorOpen( )
            Toast.makeText(this@Mainfragment.context, "Открытие двери", Toast.LENGTH_SHORT).show()

        }
    }
}

