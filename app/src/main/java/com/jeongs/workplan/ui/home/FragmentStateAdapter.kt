package com.jeongs.workplan.ui.home

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentStateAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val pageCount = Int.MAX_VALUE
    var fragments = mutableListOf<CalendarFragment>()
    val firstFragmentPosition = Int.MAX_VALUE / 2
    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment{
        val calendarFragment = CalendarFragment()
        calendarFragment.pageIndex = position
        Log.e("생성한 포지션", position.toString())
        return calendarFragment

    }

}