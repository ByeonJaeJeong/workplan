package com.jeongs.workplan.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.ListAdapter
import com.jeongs.workplan.R
import com.jeongs.workplan.db.CalendarDAO

class CalendarAdapter(val view: View) :
        ListAdapter<CalendarDAO, ViewHolder>(
                CalendarAdapterDiffcallback()) {


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
                Toast.makeText(it.context, "클릭이벤트 작동",position).show()
                val bundle= Bundle()
                bundle.putInt("year",item.year)
                bundle.putInt("month",item.month)
                bundle.putInt("day",item.day)


            })
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
}