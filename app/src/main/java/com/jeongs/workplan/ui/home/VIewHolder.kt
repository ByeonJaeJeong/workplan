package com.jeongs.workplan.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.databinding.DayItemBinding
import com.jeongs.workplan.db.CalendarDAO

class  ViewHolder private constructor(
private val binding: DayItemBinding
) : RecyclerView.ViewHolder(binding.root){
    val dateNumber : TextView =binding.textViewDateNumber
    lateinit var navController: NavController

    init {
        Log.v("Tag", "ViewHolder - init() called")

    }

    fun bind(item: CalendarDAO){
        binding.calendarInfo=item
        binding.executePendingBindings()
    }

    companion object{
        fun  from(parent: ViewGroup): ViewHolder{
            val layoutInflater=
                    LayoutInflater.from(parent.context)
            val binding=DayItemBinding.inflate(layoutInflater,parent,false)
            return ViewHolder(binding)
        }
    }
}