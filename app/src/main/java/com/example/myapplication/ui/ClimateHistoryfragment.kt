package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ClimateHistoryfragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_climatehistory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(ClimateComfortfragment())
        }
    }

    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }
}
