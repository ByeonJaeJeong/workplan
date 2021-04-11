package com.jeongs.workplan.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.jeongs.workplan.db.CalendarDAO

class CalendarAdapterDiffcallback : DiffUtil.ItemCallback<CalendarDAO>() {
    override fun areItemsTheSame(oldItem: CalendarDAO, newItem: CalendarDAO): Boolean {
        return oldItem.day == newItem.day  //예전의 날짜와 지금의 날짜가 같은지 여부를 불리언으로 리턴
    }

    override fun areContentsTheSame(oldItem: CalendarDAO, newItem: CalendarDAO): Boolean {
        return oldItem == newItem       //Calender Info 가 지금것과 예전것이 같은지 여부 판단
    }
}