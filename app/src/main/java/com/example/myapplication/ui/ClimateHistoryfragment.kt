package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ClimateHistoryItem
import com.example.myapplication.data.DataClimateHistory
import com.example.myapplication.data.DoorHistoryItem
import com.example.myapplication.web.WebClient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
        val rvClimate = view.findViewById<RecyclerView>(R.id.rvCliamte)
        val adapter = Adapter()
        rvClimate.layoutManager = LinearLayoutManager(requireContext())
        rvClimate.adapter = adapter
        lifecycleScope.launch{
            adapter.setNewList(WebClient.getClimateHistory())
        }
        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(ClimateComfortfragment())
        }

    }

    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }

    fun getClimateHistory(){
        lifecycleScope.launch{
            try {
                val ClimateHistory = WebClient.getClimateHistory()
                Log.d("Mainfragment", ClimateHistory.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@ClimateHistoryfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>(){

        private val list = mutableListOf<ClimateHistoryItem>()

        fun setNewList(newList: List<ClimateHistoryItem>){
            list.clear()
            list.addAll(newList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_climate,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(list[position])
        }

        class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer {
            fun bind(item: ClimateHistoryItem){
                containerView.davlenie.text = item.davlenie.toString()
                containerView.Vlaznost.text = item.Vlaznost.toString()
                containerView.temperature.text = item.temperature.toString()
                containerView.temperature_home.text = item.temperature_home.toString()
                containerView.time.text= item.time
            }
        }
    }
}
