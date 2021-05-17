package com.jeongs.workplan.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.MainActivity
import com.jeongs.workplan.R
import com.jeongs.workplan.TestActivity2
import com.jeongs.workplan.db.CalendarDAO

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView :View): RecyclerView.ViewHolder(itemView){



    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val calendar = Calendar.getInstance()
        val nowDay = calendar.get(Calendar.DAY_OF_MONTH)
        val nowYear =calendar.get(Calendar.YEAR)
        val nowMonth=calendar.get(Calendar.MONTH)+1

        // 0일경우 날짜표시 x
        if (item.day == 0) {
            holder.dateNumber.visibility = View.GONE
        }
        //일요일
        if (item.week == 0) {
            holder.dateNumber.setTextColor(R.color.FireBrick)
        }
        //토요일
        if (item.week == 6) {
            holder.dateNumber.setTextColor(R.color.blue)
        }
        //오늘 날짜에 표기
        if (item.year == nowYear && item.month == nowMonth && item.day == nowDay){
            holder.dateNumber.setBackgroundColor(R.color.ThemeColors)
            holder.dateNumber.setTextColor(Color.WHITE)
        }
        holder.bind(item)
        if(item.day != 0){
            //item Click event
            holder.itemView.setOnClickListener(View.OnClickListener {

                val intent = Intent(it.context, TestActivity2::class.java)
                intent.putExtra("year",item.year)
                intent.putExtra("month",item.month)
                intent.putExtra("day",item.day)

                it.context.startActivity(intent)
            })
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_view,parent,false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}