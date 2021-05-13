package com.jeongs.workplan.ui.home

import android.os.Bundle

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.jeongs.workplan.R
import com.jeongs.workplan.db.CalendarDAO
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.icu.util.Calendar
import android.system.Os
import android.util.Log
import android.view.*
import android.widget.Toast

// 추가로 만들어야 하는 내용
// scroll event 를 이용해서 달력 월이동
// 일 눌렸을때 하는 역할(calendar 의 주기능 추가 할예정)
// 통계와 더보기 버튼 역할

//현재 오류
//메인에서 월 변경후 통계나 더보기 누른후 메인으로 돌아오면 월이 다시최근 월로 변경되어있음

class HomeFragment : Fragment() , View.OnClickListener{

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: CalendarAdapter
    lateinit var calendar: Calendar
    lateinit var root:View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
         root  = inflater.inflate(R.layout.fragment_home, container, false)

        if (homeViewModel.year.toInt() == 0 && homeViewModel.month.toInt() == 0){
            //처음 접속시 데이터가 없으므로 현재날짜를 반환
             calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)+1
            homeViewModel.selectCalendar(year,month)
        }

        var select_date:TextView = root.findViewById(R.id.select_date)
        select_date.text=homeViewModel.year.toString()+"년 "+homeViewModel.month.toString()+"월"

        select_date.setOnClickListener(this)

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= CalendarAdapter(view)
        root.calendar.adapter =adapter
        calendar = Calendar.getInstance()
        //현재 날짜 정보 입력
        calendar.set(Calendar.YEAR,homeViewModel.year)
        calendar.set(Calendar.MONTH,homeViewModel.month-1)
        calendar.set(Calendar.DAY_OF_MONTH,1)

        val year=calendar.get(Calendar.YEAR)
        val maxDate =calendar.getActualMaximum(Calendar.DATE)
        val week =calendar.get(Calendar.DAY_OF_WEEK)
        val month = calendar.get(Calendar.MONTH)+1
        Toast.makeText(view.context,week.toString(),Toast.LENGTH_SHORT).show()
        val list = MutableList(week-1, init = { CalendarDAO(year,month,0,0) })
        for (i in 1..maxDate) {
            val week_day = (week-1+i) % 7
            list.add(CalendarDAO(year,month,i,week_day))
        }
        adapter.submitList(list)
    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.select_date->{
                //월 변경 메소드
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

