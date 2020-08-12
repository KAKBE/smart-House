package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.data.DataLight
import com.example.myapplication.data.DataLightSleep
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class Lightfragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_light, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lightSwitch = view.findViewById<Switch>(R.id.switch4)
        val seekBarMin = view.findViewById<SeekBar>(R.id.seekBar4)
        val seekBarMax = view.findViewById<SeekBar>(R.id.seekBar7)
        lightSwitch.setOnCheckedChangeListener(){ compoundButton: CompoundButton, b: Boolean ->
            setLight(b)
        }
    }
    fun setLight(b: Boolean){
        lifecycleScope.launch{
            try {
                WebClient.setLightLux(DataLight(b, 30, 80))
            }catch(ex: HttpException){
                Toast.makeText(this@Lightfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getLight(){
        lifecycleScope.launch{
            val light = WebClient.getLightLux()
            Toast.makeText(this@Lightfragment.context, "${light.state} ${light.level_max}"+" ${light.level_min}", Toast.LENGTH_SHORT).show()
            Log.d("Mainfragment","${light.state} ${light.level_max}"+" ${light.level_min}")
        }
    }

    fun getLightSleep(){
        lifecycleScope.launch{
            try {
                val lightSleep = WebClient.getLightSleep()
                Log.d("Mainfragment", lightSleep.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@Lightfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setLightSleep(){
        lifecycleScope.launch{
            try {
                WebClient.setLightSleep(DataLightSleep(true,40,70,"22:00"))
            }catch(ex: HttpException){
                Toast.makeText(this@Lightfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
