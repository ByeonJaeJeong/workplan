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

class MonthSelectDialog(date: String) : BottomSheetDialogFragment(),View.OnClickListener {
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

    fun save() {
        Log.e("sharedViewmodel","dissmiss작동")
        val firstTime = Calendar.getInstance()
        val first_month = firstTime.get(Calendar.YEAR)*12+firstTime.get(Calendar.MONTH)+1
        val selectDate = Calendar.getInstance().apply {
            set(Calendar.YEAR,select_year)
            set(Calendar.MONTH,select_month)
        }
        val select_month = selectDate.get(Calendar.YEAR)*12+selectDate.get(Calendar.MONTH)
        var resultMonth = first_month-select_month
        Log.e("resultMonth", resultMonth.toString())
        sharedViewModel.pageIndex= -resultMonth

    }
    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        var ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.detach(fragment).attach(fragment).commit()
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
               select_month = 1
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_2->{
                select_month =2
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_3->{
                select_month = 3
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_4->{
                select_month = 4
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_5->{
                select_month = 5
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_6->{
                select_month = 6
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_7->{
                select_month = 7
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_8->{
                select_month = 8
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_9->{
                select_month = 9
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_10->{
                select_month = 10
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_11->{
                select_month = 11
                save()
                dialog?.dismiss()
            }
            R.id.monthBtn_12->{
                select_month = 12
                save()
                dialog?.dismiss()
            }
        }
    }
}