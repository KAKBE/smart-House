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
import retrofit2.HttpException

class Mainfragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = layoutInflater.inflate(R.layout.fragment_main, container, false)
        val btn = v.findViewById<Button>(R.id.button9)
        val btn1 = v.findViewById<Button>(R.id.button12)
        getLight()
        accessPhoto()

        btn.setOnClickListener{
            naivgateToFragment(Lightfragment())
        }
        btn1.setOnClickListener{

        }
        return v
    }

    fun naivgateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }

    fun getLight(){
        lifecycleScope.launch{
            val light = WebClient.getLightLux()
            Toast.makeText(this@Mainfragment.context, "${light.state} ${light.level_max}"+" ${light.level_min}", Toast.LENGTH_SHORT).show()
            Log.d("Mainfragment","${light.state} ${light.level_max}"+" ${light.level_min}")
        }
    }
    fun setLight(){
        lifecycleScope.launch{
            try {
                WebClient.setLightLux(DataLight(true, 30, 80))
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun accessPhoto(){
        lifecycleScope.launch {
            val photo = WebClient.getAccessPhoto()
            Log.d("Mainfragment", "${photo.photo}")
        }
    }

}


