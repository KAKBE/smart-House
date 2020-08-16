package com.example.myapplication.ui

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private var min = 0
    private var max = 0
    private var sleepmin = 0
    private var sleepmax = 0
    private var hour = 0
    private var minute =  0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lightSwitch = view.findViewById<Switch>(R.id.switch4)
        val seekBarMin = view.findViewById<SeekBar>(R.id.seekBar4)
        val seekBarMax = view.findViewById<SeekBar>(R.id.seekBar7)
        val seekBarMinsleep = view.findViewById<SeekBar>(R.id.seekBar5)
        val seekBarMaxsleep = view.findViewById<SeekBar>(R.id.seekBar8)
        val textView = view.findViewById<TextView>(R.id.textView)

        textView.setOnClickListener{
            val dialog = TimePickerDialog(requireContext(),
                TimePickerDialog.OnTimeSetListener { p0, p1, p2 ->
                    hour= p1
                    minute = p2
                },12, 0, true)
            dialog.show()
        }
        lightSwitch.setOnCheckedChangeListener(){ compoundButton: CompoundButton, b: Boolean ->
            setLight(b)
        }
        val switchLight = view.findViewById<Switch>(R.id.switch4)
        switchLight.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                setLight(p1)
            }
        })

        val switchSleep = view.findViewById<Switch>(R.id.switch5)
        switchSleep.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                setLightSleep(p1)
            }
        })

        seekBarMin.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                min = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        seekBarMax.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                max = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        seekBarMinsleep.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                sleepmin = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        seekBarMaxsleep.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                sleepmax = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }



    fun setLight(b: Boolean){
        lifecycleScope.launch{
            try {
                WebClient.setLightLux(DataLight(b, min, max))
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

    fun setLightSleep(b: Boolean){
        lifecycleScope.launch{
            try {
                WebClient.setLightSleep(DataLightSleep(b,sleepmin,sleepmax,"22:00"))
            }catch(ex: HttpException){
                Toast.makeText(this@Lightfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
