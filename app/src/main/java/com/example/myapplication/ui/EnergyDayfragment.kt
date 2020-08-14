package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.DoorHistoryItem
import com.example.myapplication.data.ElectroHistoryItem
import com.example.myapplication.web.WebClient
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_climate.view.*
import kotlinx.android.synthetic.main.item_climate.view.date
import kotlinx.android.synthetic.main.item_climate.view.time
import kotlinx.android.synthetic.main.item_electro.view.*
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EnergyDayfragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_energyday, container, false)


        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val r = view.findViewById<RecyclerView>(R.id.rvDay)
        val adapter = Adapter()
        r.layoutManager = LinearLayoutManager(requireContext())
        r.adapter = adapter
        lifecycleScope.launch{
            adapter.setNewList(WebClient.getElectricityConsumptionHistory().climate)
        }
        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(EnergyWeekfragment())
        }
    }
    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }

    fun getElectricityConsumptionHistory(){
        lifecycleScope.launch{
            try {
                val ElectricityConsumptionHistory = WebClient.getElectricityConsumptionHistory()
                Log.d("Mainfragment", ElectricityConsumptionHistory.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@EnergyDayfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>(){

        private val list = mutableListOf<ElectroHistoryItem>()

        fun setNewList(newList: List<ElectroHistoryItem>){
            list.clear()
            list.addAll(newList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_electro,parent,false)
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
            fun bind(item: ElectroHistoryItem){
                containerView.date.text = item.time_day
                containerView.kol.text = item.every_day_consumption.toString()

            }
        }
    }
}
