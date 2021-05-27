
package com.jeongs.workplan.ui.home

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeongs.workplan.R
import android.icu.util.Calendar
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.text.SimpleDateFormat

// 추가로 만들어야 하는 내용
// scroll event 를 이용해서 달력 월이동
// 일 눌렸을때 하는 역할(calendar 의 주기능 추가 할예정)
// 통계와 더보기 버튼 역할

//viewpager2 를 이용한 recycleview로 변경할 예정

class HomeFragment : Fragment(){

    private lateinit var  sharedViewModel: SharedViewModel
    private lateinit var adapter: CalendarAdapter
    lateinit var root:View
    private lateinit var dateCalendar : Calendar
    lateinit var firstFragmentStateAdapter :FragmentStateAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            sharedViewModel= ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         root  = inflater.inflate(R.layout.fragment_home, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select_date.setOnClickListener {
            val bottomDialog: MonthSelectDialog = MonthSelectDialog(it.select_date.text.toString()) {
                //연도 월을 받아 날짜갱신후 fragment 새로고침
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
                var length = it.length
                var year = it.substring(0, length - 2).toInt()
                var month = it.substring(length - 2, length).toInt()
                val calendar : Calendar= Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                val currentCalendar :Calendar =Calendar.getInstance()
                currentCalendar.set(Calendar.YEAR,sharedViewModel.year)
                currentCalendar.set(Calendar.MONTH, sharedViewModel.month)
                Log.e("data-1",calendar.get(Calendar.YEAR).toString()+","+calendar.get(Calendar.MONTH).toString())
                Log.e("data-2",currentCalendar.get(Calendar.YEAR).toString()+","+currentCalendar.get(Calendar.MONTH).toString())
                viewpager2.currentItem= (Int.MAX_VALUE/2)+sharedViewModel.get_position(calendar,currentCalendar)
                Log.e("data",sharedViewModel.get_position(calendar, currentCalendar).toString())
                Log.e("data_position",viewpager2.currentItem.toString())

            }
            //다이어로그 출력
            activity?.supportFragmentManager?.let { it1 -> bottomDialog.show(it1, bottomDialog.tag) }
        }
        initView(sharedViewModel)
    }

    //calendar 생성하는 메소드
    fun initView(sharedViewModel: SharedViewModel){
        Log.e("refresh 테스트","initView작동")
        firstFragmentStateAdapter = FragmentStateAdapter(requireActivity(),view)
        viewpager2.adapter  = firstFragmentStateAdapter
        viewpager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        firstFragmentStateAdapter.apply {
            viewpager2.setCurrentItem(this.firstFragmentPosition,false)
        }
        viewpager2.setPageTransformer { page, position ->
            if(position == 0.0f) {
                Log.e("data-position",sharedViewModel.month.toString())
                dateCalendar = Calendar.getInstance()
                dateCalendar.set(Calendar.YEAR,sharedViewModel.year)
                dateCalendar.set(Calendar.MONTH,sharedViewModel.month)
                dateCalendar.add(Calendar.MONTH,(viewpager2.currentItem-(Int.MAX_VALUE/2)))
                val date = dateCalendar.time
                val dateformat = SimpleDateFormat("yyyy년 MM월")
                select_date.text= dateformat.format(date)
            }
        }


    }

    override fun onDestroyView() {
        sharedViewModel.month= dateCalendar.get(Calendar.MONTH)
        sharedViewModel.year = dateCalendar.get(Calendar.YEAR)
        super.onDestroyView()
    }
}

