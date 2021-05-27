package com.jeongs.workplan.ui.home

import androidx.lifecycle.ViewModel
import java.util.*

//뷰 모델 클래스는 수명주기와 상관없이 데이터를 사용하기 위해 사용함
//주로 수명주기를 고려하여 UI 관련 데이터를 저장하고 관리하도록 설계
//LiveData랑 같이 사용한다고함 *공부 추가로 할것
class SharedViewModel(var year :Int = 0,
                      var month : Int = 0,
                      var pageIndex :Int = 0) : ViewModel() {



    init {
        val calendar= Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
    }
    fun selectCalendar(year: Int,month: Int){
        this.year= year
        this.month=month
    }
    //calendar 는 선택된 위치.calendar2 기준치
    fun get_position(calendar: android.icu.util.Calendar, calendar2: android.icu.util.Calendar):Int{

        return ((calendar.get(Calendar.YEAR)-calendar2.get(Calendar.YEAR))*12+(calendar.get(Calendar.MONTH)-calendar2.get(Calendar.MONTH)))
    }



}