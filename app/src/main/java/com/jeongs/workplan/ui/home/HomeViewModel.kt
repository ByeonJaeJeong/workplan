package com.jeongs.workplan.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Month
import java.time.Year

//뷰 모델 클래스는 수명주기와 상관없이 데이터를 사용하기 위해 사용함
//주로 수명주기를 고려하여 UI 관련 데이터를 저장하고 관리하도록 설계
//LiveData랑 같이 사용한다고함 *공부 추가로 할것
class HomeViewModel : ViewModel() {

    var year : Int = 0
    var month : Int =0
    var day : Int = 0
    var dayOfWeek : Int =0

    fun selectCalendar(year2: Int,month2: Int){
        year= year2
        month=month2
    }


}