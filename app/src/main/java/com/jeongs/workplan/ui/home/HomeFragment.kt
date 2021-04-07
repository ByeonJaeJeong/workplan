package com.jeongs.workplan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.R
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() , View.OnClickListener{

    private lateinit var homeViewModel: HomeViewModel



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (homeViewModel.year.toInt() == 0 && homeViewModel.day.toInt() == 0){
            val calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)+1
            var day =calendar.get(Calendar.DAY_OF_MONTH)
            homeViewModel.addTime(year,month,day)
        }
        var select_date:TextView = root.findViewById(R.id.select_date)
        select_date.text=homeViewModel.year.toString()+"년"+homeViewModel.month.toString()+"월"

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}