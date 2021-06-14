
package com.jeongs.workplan.ui.dashboard

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.archit.calendardaterangepicker.customviews.CalendarListener
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarViewApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.jeongs.workplan.R
import kotlinx.android.synthetic.main.periodfilter_bottomsheet.*
import kotlinx.android.synthetic.main.periodfilter_bottomsheet.view.*
import java.util.*

class PeriodFilterBottomSheet(val dashboardViewModel: DashboardViewModel) : BottomSheetDialogFragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resources.configuration.setLocale(Locale.KOREAN)//한국어로 변경
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.periodfilter_bottomsheet,container, false)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            view.date_text.text= dashboardViewModel.text.value
        var text= dashboardViewModel.text.value?.split("-")!!
        val calendar = Calendar.getInstance()
        Log.e("text 체크",text[0]+","+text[1]+","+text[2])
        calendar.set(text[0].toInt(),text[1].toInt(),text[2].substring(0,2).toInt())
        view.calendar.isVisible= false
        dashboardViewModel.text.observe(viewLifecycleOwner,{
            view.date_text.text=it
        })

        //선택 버튼 동작 이벤트
        filter_select.setOnClickListener {
            dashboardViewModel.filter_change(4)
            view.calendar.isVisible= true
        }
        //일주일 버튼 동작 이벤트
        filter_week.setOnClickListener {
            dashboardViewModel.filter_change(0)
            view.calendar.isVisible= false
        }
        //첫날부터 끝날까지 한달
        filter_thisMonth.setOnClickListener {
            dashboardViewModel.filter_change(1)
            view.calendar.isVisible= false
        }
        //현재 진행중인 날부터 다음달 그날까지
        filter_month.setOnClickListener {
            dashboardViewModel.filter_change(2)
            view.calendar.isVisible= false
        }
        //3개월 버튼 동작 이벤트
        filter_threeMonth.setOnClickListener {
            dashboardViewModel.filter_change(3)
            view.calendar.isVisible= false
        }

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(view?.calendar?.startDate!=null && view?.calendar?.endDate !=null && dashboardViewModel.filter_index == 4) {
            val startDate= view?.calendar?.startDate?.time!!
            val endDate = view?.calendar?.endDate?.time!!
            val jump = (view?.calendar?.endDate?.timeInMillis!!.minus(view?.calendar?.startDate?.timeInMillis!!) / 86400000).toInt()
            dashboardViewModel.jump_day=jump
            dashboardViewModel.setDate(startDate,endDate)

        }
    }


}




