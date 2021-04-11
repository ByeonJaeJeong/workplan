package com.jeongs.workplan.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jeongs.workplan.db.CalendarDAO

object BindingAdapters {

    /**
     날짜 데이터 생성
     */
    @JvmStatic
    @BindingAdapter("setDate")
    fun TextView.setDate(item: CalendarDAO){
        item?.let {
            text= it.day.toString()
        }
    }

}