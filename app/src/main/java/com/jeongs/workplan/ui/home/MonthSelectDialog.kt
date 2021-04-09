package com.jeongs.workplan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeongs.workplan.R

class MonthSelectDialog(year:Int,month:Int, val Date : (String) ->Unit) : BottomSheetDialogFragment(),View.OnClickListener {
        var year = year
        var month = month
        lateinit var  yearTextureView: TextView
        private lateinit var homeViewModel: HomeViewModel




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View=
        inflater.inflate(R.layout.bottom_sheet,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yearTextureView = view.findViewById(R.id.month_grid_year)


        yearTextureView.text=year.toString()+"년"

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
                year=year-1
                yearTextureView.text=year.toString()+"년"
            }
            R.id.grid_year_rightBtn->{
                year=year+1
                yearTextureView.text=year.toString()+"년"
            }
            R.id.monthBtn_1->{
                Date(year.toString()+"/1")
                dialog?.dismiss()
            }
            R.id.monthBtn_2->{
                Date(year.toString()+"/2")
                dialog?.dismiss()
            }
            R.id.monthBtn_3->{
                Date(year.toString()+"/3")
                dialog?.dismiss()
            }
            R.id.monthBtn_4->{
                Date(year.toString()+"/4")
                dialog?.dismiss()
            }
            R.id.monthBtn_5->{
                Date(year.toString()+"/5")
                dialog?.dismiss()
            }
            R.id.monthBtn_6->{
                Date(year.toString()+"/6")
                dialog?.dismiss()
            }
            R.id.monthBtn_7->{
                Date(year.toString()+"/7")
                dialog?.dismiss()
            }
            R.id.monthBtn_8->{
                Date(year.toString()+"/8")
                dialog?.dismiss()
            }
            R.id.monthBtn_9->{
                Date(year.toString()+"/9")
                dialog?.dismiss()
            }
            R.id.monthBtn_10->{
                Date(year.toString()+"/10")
                dialog?.dismiss()
            }
            R.id.monthBtn_11->{
                Date(year.toString()+"/11")
                dialog?.dismiss()
            }
            R.id.monthBtn_12->{
                Date(year.toString()+"/12")
                dialog?.dismiss()
            }
        }
    }
}