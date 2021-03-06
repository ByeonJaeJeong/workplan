package com.jeongs.workplan.ui.calendar

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentStateAdapter(fragmentActivity: FragmentActivity,val view: View?) : FragmentStateAdapter(fragmentActivity) {

    private val pageCount = Int.MAX_VALUE
    var fragments = mutableListOf<CalendarFragment>()
    val firstFragmentPosition = Int.MAX_VALUE / 2
    override fun getItemCount(): Int = Int.MAX_VALUE


    override fun createFragment(position: Int): Fragment{
        val calendarFragment = CalendarFragment(view)
        calendarFragment.pageIndex = position
        return calendarFragment
    }


}