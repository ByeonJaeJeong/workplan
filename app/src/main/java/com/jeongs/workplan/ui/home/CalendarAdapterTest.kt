package com.jeongs.workplan.ui.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.Math.abs
import java.time.LocalDateTime
import java.util.*

class CalendarAdapterTest : FragmentStateAdapter {

    companion object{
        const val  START_POSITION = Int.MAX_VALUE / 2
    }

    constructor(homeFragment : HomeFragment) : super(homeFragment)
    constructor(fragment : Fragment) :super(fragment)
    constructor(fragmentManager : FragmentManager, lifecycle: Lifecycle): super(fragmentManager,lifecycle)

    override fun getItemCount(): Int = Int.MAX_VALUE
    override fun getItemId(position: Int): Long {
        //현재 날짜와 월을 가져옴
        val cal = Calendar.getInstance()
        var currentYear = cal.get(Calendar.YEAR)
        var currentMonth = cal.get(Calendar.MONTH)
       //이동하는 변수
        val move = position- START_POSITION
        val bias= if(move < 0) -1 else 1
        //더하거나 뺄때 넘으면 연은 추가하거나 삭제하고
        //월은 초기화
        val moveYear = abs(move) /12 * bias
        val moveMonth = abs(move) % 12 * bias

        //현재 연도에 이동하는 연도를 추가
        currentYear += moveYear
        when {
            (currentMonth + moveMonth) <1 ->{
                currentMonth = 12 + (currentMonth + moveMonth)
                currentYear--
            }
            (currentMonth + moveMonth) >12 ->{
                currentMonth = (currentMonth + moveMonth) - 12
                currentYear++
            }
            else->{
                currentMonth = (currentMonth + moveMonth)
            }
        }
        //연도 + 월을 숫자로 정수형 타입으로 출력
        return  (currentYear*100 + currentMonth).toLong()
    }

    override fun containsItem(itemId: Long): Boolean = 200000L < itemId && itemId < 210001L
    override fun createFragment(position: Int): Fragment {
        val itemId = getItemId(position)
        return HomeFragment().apply {
            arguments = Bundle().apply {
                putLong("Year" , itemId / 100L)
                putLong("month", itemId % 100L)
            }
        }
    }
}