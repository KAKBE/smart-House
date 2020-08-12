package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.web.WebClient
import kotlinx.coroutines.launch
import retrofit2.HttpException

class Accessfragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_access, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(AccessСallfragment())
        }

        val btn5=  view.findViewById<Button>(R.id.button5)
        btn5.setOnClickListener{
            navigateToFragment(AccessPhotofragment())
        }

        val btn4=  view.findViewById<Button>(R.id.button4)
        btn4.setOnClickListener{
            navigateToFragment(AccessСallfragment())
        }

        val btn = view.findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            doorOpen()
        }
    }
    fun doorOpen(){
        lifecycleScope.launch {
            try {
                WebClient.setDoorOpen()
                Toast.makeText(this@Accessfragment.context, "Открытие двери", Toast.LENGTH_SHORT)
                    .show()
            }catch(ex: HttpException){
                Toast.makeText(this@Accessfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }


}
