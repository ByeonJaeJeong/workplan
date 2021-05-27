package com.jeongs.workplan.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeongs.workplan.MainActivity
import com.jeongs.workplan.R
import java.util.*

class MonthSelectDialog(date: String,val itemClick: (String) ->Unit) : BottomSheetDialogFragment(),View.OnClickListener {
        var year = date.substring(0,4).toInt()
        var month = date.substring(6,8).toInt()
        var select_year = year
        var select_month = 0
        lateinit var  yearTextureView: TextView
        private lateinit var  sharedViewModel: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.run{
            sharedViewModel= ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View=
        inflater.inflate(R.layout.bottom_sheet,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yearTextureView = view.findViewById(R.id.month_grid_year)


        yearTextureView.text=select_year.toString()+"년"

        val leftButton : ImageButton = view.findViewById(R.id.grid_year_leftBtn)
        val rightButton : ImageButton = view.findViewById(R.id.grid_year_rightBtn)
        val monthBtn_1 : Button = view.findViewById(R.id.monthBtn_1)
        val monthBtn_2 : Button = view.findViewById(R.id.monthBtn_2)
        val monthBtn_3 : Button = view.findViewById(R.id.monthBtn_3)
        val monthBtn_4 : Button = view.findViewById(R.id.monthBtn_4)
        val monthBtn_5 : Button = view.findViewById(R.id.monthBtn_5)
        val monthBtn_6: Button = view.findViewById(R.id.monthBtn_6)
        val monthBtn_7 : Button = view.findViewById(R.id.monthBtn_7)
        val monthBtn_8 : Button = view.findViewById(R.id.monthBtn_8)
        val monthBtn_9 : Button = view.findViewById(R.id.monthBtn_9)
        val monthBtn_10 : Button = view.findViewById(R.id.monthBtn_10)
        val monthBtn_11 : Button = view.findViewById(R.id.monthBtn_11)
        val monthBtn_12 : Button = view.findViewById(R.id.monthBtn_12)

        leftButton.setOnClickListener(this)
        rightButton.setOnClickListener(this)
        monthBtn_1.setOnClickListener(this)
        monthBtn_2.setOnClickListener(this)
        monthBtn_3.setOnClickListener(this)
        monthBtn_4.setOnClickListener(this)
        monthBtn_5.setOnClickListener(this)
        monthBtn_6.setOnClickListener(this)
        monthBtn_7.setOnClickListener(this)
        monthBtn_8.setOnClickListener(this)
        monthBtn_9.setOnClickListener(this)
        monthBtn_10.setOnClickListener(this)
        monthBtn_11.setOnClickListener(this)
        monthBtn_12.setOnClickListener(this)



    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.grid_year_leftBtn->{
                select_year -= 1
                yearTextureView.text=select_year.toString()+"년"
            }
            R.id.grid_year_rightBtn->{
                select_year += 1
                yearTextureView.text=select_year.toString()+"년"
            }
            R.id.monthBtn_1->{
                itemClick(select_year.toString()+"00")
                dialog?.dismiss()
            }
            R.id.monthBtn_2->{
                itemClick(select_year.toString()+"01")
                dialog?.dismiss()
            }
            R.id.monthBtn_3->{
                itemClick(select_year.toString()+"02")
                dialog?.dismiss()
            }
            R.id.monthBtn_4->{
                itemClick(select_year.toString()+"03")
                dialog?.dismiss()
            }
            R.id.monthBtn_5->{
                itemClick(select_year.toString()+"04")
                dialog?.dismiss()
            }
            R.id.monthBtn_6->{
                itemClick(select_year.toString()+"05")
                dialog?.dismiss()
            }
            R.id.monthBtn_7->{
                itemClick(select_year.toString()+"06")
                dialog?.dismiss()
            }
            R.id.monthBtn_8->{
                itemClick(select_year.toString()+"07")
                dialog?.dismiss()
            }
            R.id.monthBtn_9->{
                itemClick(select_year.toString()+"08")
                dialog?.dismiss()
            }
            R.id.monthBtn_10->{
                itemClick(select_year.toString()+"09")
                dialog?.dismiss()
            }
            R.id.monthBtn_11->{
                itemClick(select_year.toString()+"10")
                dialog?.dismiss()
            }
            R.id.monthBtn_12->{
                itemClick(select_year.toString()+"11")
                dialog?.dismiss()
            }
        }
    }
}