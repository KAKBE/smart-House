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
import com.example.myapplication.data.DataClimateComfort
import com.example.myapplication.data.DataClimateStation
import com.example.myapplication.data.DataLight
import com.example.myapplication.data.DataLightSleep
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class Mainfragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = layoutInflater.inflate(R.layout.fragment_main, container, false)
        val btn = v.findViewById<Button>(R.id.button9)
        val btn1 = v.findViewById<Button>(R.id.button12)
        val btnClimate = v.findViewById<Button>(R.id.button13)
        getLight()
        accessPhoto()

        btn.setOnClickListener{
            navigateToFragment(Lightfragment())
        }
        btn1.setOnClickListener{

        }
        btnClimate.setOnClickListener{
            navigateToFragment(ClimateControlfragment())
        }
        return v
    }

    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }

    fun getLight(){
        lifecycleScope.launch{
            val light = WebClient.getLightLux()
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

    fun getLightSleep(){
        lifecycleScope.launch{
            try {
                val lightSleep = WebClient.getLightSleep()
                Log.d("Mainfragment", lightSleep.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setLightSleep(){
        lifecycleScope.launch{
            try {
                WebClient.setLightSleep(DataLightSleep(true,40,70,"22:00"))
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getDoorHistory(){
        lifecycleScope.launch{
            try {
                val DoorHistory = WebClient.getDoorHistory()
                Log.d("Mainfragment", DoorHistory.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getClimateStation(){
        lifecycleScope.launch{
            try {
                val ClimateStation = WebClient.getClimateStation()
                Log.d("Mainfragment", ClimateStation.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getClimateHistory(){
        lifecycleScope.launch{
            try {
                val ClimateHistory = WebClient.getClimateHistory()
                Log.d("Mainfragment", ClimateHistory.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getClimateComfort(){
        lifecycleScope.launch{
            try {
                val ClimateComfort = WebClient.getClimateComfort()
                Log.d("Mainfragment", ClimateComfort.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setClimateComfort(){
        lifecycleScope.launch{
            try {
                WebClient.setClimateComfort(DataClimateComfort(true,40,70,45,50))
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getClimateOnline(){
        lifecycleScope.launch{
            try {
                val ClimateOnline = WebClient.getClimateOnline()
                Log.d("Mainfragment", ClimateOnline.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Mainfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getElectricityConsumptionHistory(){
        lifecycleScope.launch{
            try {
                val ElectricityConsumptionHistory = WebClient.getElectricityConsumptionHistory()
                Log.d("Mainfragment", ElectricityConsumptionHistory.toString())
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


