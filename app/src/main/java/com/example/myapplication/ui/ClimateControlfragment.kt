package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.data.DataClimateStation
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch

class ClimateControlfragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_climate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val windowSwitch = view.findViewById<Switch>(R.id.switch2)
        windowSwitch.setOnCheckedChangeListener {
                compoundButton: CompoundButton?, b: Boolean ->
            windowOpen(b)
        }
        val pechkaSwtich = view.findViewById<Switch>(R.id.switch1)
        pechkaSwtich.setOnCheckedChangeListener {
                compoundButton: CompoundButton?, b: Boolean ->
            pechkaAllow(b)
        }
        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(ClimateHistoryfragment())
        }
    }

    fun windowOpen(b: Boolean){
        lifecycleScope.launch{
            WebClient.setClimateClimateStation(DataClimateStation(false, b, false, "", "", ""))
        }
    }

    fun pechkaAllow(b:Boolean){
        lifecycleScope.launch{
            WebClient.setClimateClimateStation(DataClimateStation(b, false, false, "", "", ""))
        }
    }
    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }
}
