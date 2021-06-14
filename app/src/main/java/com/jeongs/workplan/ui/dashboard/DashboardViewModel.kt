package com.jeongs.workplan.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DashboardViewModel : ViewModel() {

    companion object {
        var start_calendar = Calendar.getInstance()
        var end_calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd(E)", Locale.KOREAN)
    }


    private var _text = MutableLiveData<String>().apply {
        value = "날짜정보"
    }

    var text: MutableLiveData<String> = _text
    var filter_index: Int = 1  //0 = 1주일, 1 = 한달, 2 = 3달, 3 = 선택
    var jump_day: Int = 0

    //생성자
    init {
        filter_change(1)
    }

    private var start_date: String = sdf.format(start_calendar.time)
    private var end_date: String = sdf.format(end_calendar.time)
    fun filter_change(index: Int) {
        filter_index = index
        start_calendar = Calendar.getInstance()
        end_calendar = Calendar.getInstance()
        when (index) {
            0 -> {
                end_calendar.add(Calendar.DAY_OF_MONTH, 7)
                end_calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
            1 -> {
                start_calendar.set(Calendar.DAY_OF_MONTH, start_calendar.getActualMinimum(Calendar.DAY_OF_MONTH))
                end_calendar.set(Calendar.DAY_OF_MONTH, start_calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            }
            2 -> {
                end_calendar.add(Calendar.MONTH, 1)
                end_calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
            3->{
                end_calendar.add(Calendar.MONTH, 3)
                end_calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
            else -> {
                end_calendar.add(Calendar.DAY_OF_MONTH, jump_day)
                end_calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
        }
        dateToString()  //캘린더로 변경할 날짜를 String타입으로 저장
        getDate()   //저장한 데이터를 변경
    }

    fun nextDate() {
        when (filter_index) {
            0 -> {//일주일
                start_calendar.add(Calendar.DAY_OF_MONTH, 7)
                end_calendar.add(Calendar.DAY_OF_MONTH, 7)
            }
            1 -> {//이번달
                start_calendar.add(Calendar.MONTH, 1)
                end_calendar.add(Calendar.MONTH, 1)
            }
            2 -> {//현재날부터 다음달
                start_calendar.add(Calendar.MONTH, 1)
                end_calendar.add(Calendar.MONTH, 1)
            }
            3 -> {//3달후
                start_calendar.add(Calendar.MONTH, 3)
                end_calendar.add(Calendar.MONTH, 3)
            }
            4->{
                start_calendar.add(Calendar.DAY_OF_MONTH, jump_day)
                end_calendar.add(Calendar.DAY_OF_MONTH, jump_day)
            }
        }
        dateToString()  //캘린더로 변경할 날짜를 String타입으로 저장
        getDate()    //저장한 데이터를 변경
    }

    fun dateToString() {
        start_date = sdf.format(start_calendar.time)
        end_date = sdf.format(end_calendar.time)
    }

    fun backDate() {
        when (filter_index) {
            0 -> {//일주일
                start_calendar.add(Calendar.DAY_OF_MONTH, -7)
                end_calendar.add(Calendar.DAY_OF_MONTH, -7)
            }
            1 -> {//이번달
                start_calendar.add(Calendar.MONTH, -1)
                end_calendar.add(Calendar.MONTH, -1)
            }
            2 -> {//현재날부터 다음달
                start_calendar.add(Calendar.MONTH, -1)
                end_calendar.add(Calendar.MONTH, -1)
            }
            3 -> {//3달뒤
                start_calendar.add(Calendar.MONTH, -3)
                end_calendar.add(Calendar.MONTH, -3)
            }
            4->{//선택한 날짜
                start_calendar.add(Calendar.DAY_OF_MONTH, -jump_day)
                end_calendar.add(Calendar.DAY_OF_MONTH, -jump_day)
            }

        }
        dateToString()  //캘린더로 변경한 데이터를 String으로 저장
        getDate()    //저장한 데이터를 변경
    }

    fun getDate() {
        _text.value = start_date + " - " + end_date
    }

    fun setDate(date1: Date, date2: Date) {
        start_calendar.time = date1
        end_calendar.time = date2
        dateToString()
        getDate()
    }

}