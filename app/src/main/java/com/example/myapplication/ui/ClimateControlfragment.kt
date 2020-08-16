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
import com.example.myapplication.data.DataClimateStation
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ClimateControlfragment:Fragment() {

    private var stateWindow = false
    private var statePechka = false
    private var stateYvlaznitel = false
    private var timePecka = ""
    private var timeOkno = ""
    private var timeYvlaznitel = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_climate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lightSwitch = view.findViewById<Switch>(R.id.switch1)
        val windowSwitch = view.findViewById<Switch>(R.id.switch2)
        val textViewPechka = view.findViewById<TextView>(R.id.textView)
        val textViewWindow = view.findViewById<TextView>(R.id.textView3)
        val textViewYvlaznitel = view.findViewById<TextView>(R.id.textView4)
        getClimateStation()
        windowSwitch.setOnCheckedChangeListener {
                compoundButton: CompoundButton?, b: Boolean ->
            windowOpen(b)
        }

        val pechkaSwtich = view.findViewById<Switch>(R.id.switch1)
        pechkaSwtich.setOnCheckedChangeListener {
                compoundButton: CompoundButton?, b: Boolean ->
            pechkaAllow(b)
        }

        val yvlaznitelSwitch = view.findViewById<Switch>(R.id.switch3)
        yvlaznitelSwitch.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                setClimateClimateStation(p1)
            }
        })
        textViewPechka.setOnClickListener{
            openTimeDialogPechka()
        }

        textViewWindow.setOnClickListener{
            openTimeDialogOkno()
        }
        textViewYvlaznitel.setOnClickListener{
            openTimeDialogYvlaznitel()
        }
        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(ClimateHistoryfragment())
        }
    }

    fun windowOpen(b: Boolean){
        lifecycleScope.launch{
            WebClient.setClimateClimateStation(DataClimateStation(statePechka, b, stateYvlaznitel, timePecka, timeOkno, timeYvlaznitel))
        }
    }

    fun pechkaAllow(b:Boolean){
        lifecycleScope.launch{
            WebClient.setClimateClimateStation(DataClimateStation(b, stateWindow, stateYvlaznitel, timePecka, timeOkno, timeYvlaznitel))
        }
    }
    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }

    fun getClimateStation(){
        lifecycleScope.launch{
            try {
                val ClimateStation = WebClient.getClimateStation()
                Log.d("Mainfragment", ClimateStation.toString())
                stateWindow = ClimateStation.okno
                statePechka = ClimateStation.pechka
                stateYvlaznitel = ClimateStation.yvlaznitel
                timeOkno = ClimateStation.time_okno
                timePecka = ClimateStation.time_pechka
                timeYvlaznitel = ClimateStation.time_yvlaznitel
                Log.d("ClimateStation", ClimateStation.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@ClimateControlfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun setClimateClimateStation(b: Boolean){
        lifecycleScope.launch{
            WebClient.setClimateClimateStation(DataClimateStation(false, false, true, "","","10:00"))
        }
    }
    fun openTimeDialogYvlaznitel(){
        var hour = 0
        var minute = 0
        val dialogYvlaznitel = TimePickerDialog(requireContext(),
            TimePickerDialog.OnTimeSetListener { p0, p1, p2 ->
                hour= p1
                minute = p2
            },12, 0, true)
        dialogYvlaznitel.show()
        timeYvlaznitel = "$hour:$minute"
    }

    fun openTimeDialogOkno(){
        var hour = 0
        var minute = 0
        val dialogOkno = TimePickerDialog(requireContext(),
            TimePickerDialog.OnTimeSetListener { p0, p1, p2 ->
                hour= p1
                minute = p2
            },12, 0, true)
        dialogOkno.show()
        timeYvlaznitel = "$hour:$minute"
    }

    fun openTimeDialogPechka(){
        var hour = 0
        var minute = 0
        val dialogPechka = TimePickerDialog(requireContext(),
            TimePickerDialog.OnTimeSetListener { p0, p1, p2 ->
                hour= p1
                minute = p2
            },12, 0, true)
        dialogPechka.show()
        timeYvlaznitel = "$hour:$minute"
    }
}
