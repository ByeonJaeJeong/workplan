package com.jeongs.workplan.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.R
import com.jeongs.workplan.db.calendarDAO

class CalendarAdapter(private  val items: ArrayList<calendarDAO>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {


    override fun getItemCount(): Int =items.size


    override fun onBindViewHolder(holder: CalendarAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it->
            Toast.makeText(it.context, "Clicked"+item.day,Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolder {
        val inflatedView= LayoutInflater.from(parent.context).inflate(R.layout.item_day,parent,false)

        return CalendarAdapter.ViewHolder(inflatedView)
    }


    class ViewHolder(v:View) : RecyclerView.ViewHolder(v){
        private  var view:View =v
        fun bind(listener: View.OnClickListener,item: calendarDAO){

        }
    }



}