package com.jeongs.workplan.ui.dashboard

import android.os.Bundle
import android.os.LocaleList
import android.support.v4.app.INotificationSideChannel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeongs.workplan.R
import com.jeongs.workplan.ui.home.SharedViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var sharedViewModel : SharedViewModel
    private lateinit var select_cal : Calendar
    var last_day :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            sharedViewModel= ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
        }
        select_cal = Calendar.getInstance()
        select_cal.set(sharedViewModel.year,sharedViewModel.month,1)
        last_day= select_cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        var nStmt_yearEnd_textView= root.findViewById<TextView>(R.id.nStmt_yearEnd_textView)
        val sdf =SimpleDateFormat("yyyy-MM-dd(E)", Locale.KOREAN)
        val lastDay_cal = Calendar.getInstance()
        lastDay_cal.set(sharedViewModel.year,sharedViewModel.month,last_day)
        dashboardViewModel.text.value=sdf.format(select_cal.time)+" - "+sdf.format(lastDay_cal.time)
        dashboardViewModel.text.observe(viewLifecycleOwner,{
            nStmt_yearEnd_textView.text=it
        })
        //좌측버튼
        root.nStmt_leftButton_textView.setOnClickListener {
            select_cal.add(Calendar.MONTH,-1)
            lastDay_cal.add(Calendar.MONTH,-1)
            last_day=lastDay_cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            lastDay_cal.set(Calendar.DAY_OF_MONTH,last_day)
            dashboardViewModel.text.value= sdf.format(select_cal.time)+" - "+sdf.format(lastDay_cal.time)
        }
        //우측버튼
        root.nStmt_rightButton_textView.setOnClickListener {
            select_cal.add(Calendar.MONTH,1)
            lastDay_cal.add(Calendar.MONTH,1)
            last_day=lastDay_cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            lastDay_cal.set(Calendar.DAY_OF_MONTH,last_day)
            dashboardViewModel.text.value= sdf.format(select_cal.time)+" - "+sdf.format(lastDay_cal.time)
        }
        //날짜 클릭시 메소드
        root.nStmt_yearEnd_textView.setOnClickListener {
            val bottomDialog = PeriodFilterBottomSheet()

            activity?.supportFragmentManager?.let { it1 -> bottomDialog.show(it1, bottomDialog.tag)}
            }


        return root
    }
}