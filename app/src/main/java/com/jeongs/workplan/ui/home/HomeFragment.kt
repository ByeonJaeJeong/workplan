package com.jeongs.workplan.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jeongs.workplan.MainActivity
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

        if (homeViewModel.year.toInt() == 0 && homeViewModel.month.toInt() == 0){
            //처음 접속시 데이터가 없으므로 현재날짜를 반환
            val calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)+1
            homeViewModel.selectCalendar(year,month)
        }

        var select_date:TextView = root.findViewById(R.id.select_date)
        select_date.text=homeViewModel.year.toString()+"년"+homeViewModel.month.toString()+"월"

        select_date.setOnClickListener(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.select_date->{
                //월 변경 메소드
                /*val dialogView = layoutInflater.inflate(R.layout.bottom_sheet,null)
                val dialog= BottomSheetDialog(v.context)
                dialog.setContentView(dialogView)
                dialog.show()*/
                val bottomDialog : MonthSelectDialog = MonthSelectDialog(homeViewModel.year,homeViewModel.month){
                    var year = it.split("/")[0]
                    var month = it.split("/")[1]

                    homeViewModel.year=year.toInt()
                    homeViewModel.month=month.toInt()
                    refreshFragment(this,parentFragmentManager)
                }
                activity?.let { bottomDialog.show(it.supportFragmentManager,bottomDialog.tag)}
            }
        }
    }

    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        var ft: FragmentTransaction = fragmentManager.beginTransaction()
        ft.detach(fragment).attach(fragment).commit()
    }
}