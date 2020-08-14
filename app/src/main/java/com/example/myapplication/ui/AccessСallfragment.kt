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
import com.example.myapplication.web.WebClient
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_accesscall.*
import kotlinx.android.synthetic.main.item_climate.view.*
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_history.view.time
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AccessСallfragment:Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accesscall, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = Adapter()
        historyView.layoutManager=LinearLayoutManager(requireContext())
        historyView.adapter = adapter
        lifecycleScope.launch{
            val history = WebClient.getDoorHistory()
            adapter.setNewList(history)
        }


        val btnNext=  view.findViewById<Button>(R.id.next1)
        btnNext.setOnClickListener{
            navigateToFragment(AccessPhotofragment())
        }
    }
    class Adapter:RecyclerView.Adapter<Adapter.ViewHolder>(){

        private val list = mutableListOf<DoorHistoryItem>()

        fun setNewList(newList: List<DoorHistoryItem>){
            list.clear()
            list.addAll(newList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_history,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(list[position])
        }

        class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),LayoutContainer{
            fun bind(item: DoorHistoryItem){
                containerView.date.text = item.date
                containerView.time.text = item.time.toString()
                containerView.mecto.text = item.mecto
            }
        }
    }

    fun navigateToFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
    }

    fun getDoorHistory(){
        lifecycleScope.launch{
            try {
                val DoorHistory = WebClient.getDoorHistory()
                Log.d("Mainfragment", DoorHistory.toString())
            }catch(ex: HttpException){
                Toast.makeText(this@AccessСallfragment.context, "Ошибка ${ex.code()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

















































































































































































































































































    //ты серьезно сюда листал?









































































    //ты стьюпид?































































































































































































































    //ты официально гений
}
