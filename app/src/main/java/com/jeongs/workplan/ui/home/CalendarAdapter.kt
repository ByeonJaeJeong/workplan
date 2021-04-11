package com.jeongs.workplan.ui.home

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jeongs.workplan.db.CalendarDAO

class CalendarAdapter(val view: View) :
        ListAdapter<CalendarDAO, ViewHolder>(
                CalendarAdapterDiffcallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        // 0일경우 날짜표시 x
        if (item.day == 0) {
            holder.dateNumber.visibility = View.GONE
        }

        //일요일
        if (item.week == 0) {
            holder.dateNumber.setTextColor(Color.parseColor("#B22222"))
        }
        //토요일
        if (item.week == 6) {
            holder.dateNumber.setTextColor(Color.BLUE)
        }
        holder.bind(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}