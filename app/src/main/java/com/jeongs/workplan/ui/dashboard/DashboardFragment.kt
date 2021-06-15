package com.jeongs.workplan.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeongs.workplan.R
import com.jeongs.workplan.ui.home.SharedViewModel
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
            sharedViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
        }
        select_cal = Calendar.getInstance()
        select_cal.set(sharedViewModel.year, sharedViewModel.month, 1)
        last_day = select_cal.getActualMaximum(Calendar.DAY_OF_MONTH)
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
       dashboardViewModel.getDate()
        dashboardViewModel.text.observe(viewLifecycleOwner,{
            nStmt_yearEnd_textView.text=it
        })
        //좌측버튼
        root.nStmt_leftButton_textView.setOnClickListener {
            Log.e("btn","좌측버튼작동")
            dashboardViewModel.backDate()
        }
        //우측버튼
        root.nStmt_rightButton_textView.setOnClickListener {
            Log.e("btn","우측버튼작동")
            dashboardViewModel.nextDate()
        }
        //날짜 클릭시 메소드
        root.nStmt_yearEnd_textView.setOnClickListener {
            val bottomDialog = PeriodFilterBottomSheet(dashboardViewModel)
            activity?.supportFragmentManager?.let { it1 -> bottomDialog.show(it1, bottomDialog.tag)}
            }
        root.nStmt_recycleView.adapter


        return root
    }
}