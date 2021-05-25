
package com.jeongs.workplan.ui.home

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeongs.workplan.R
import android.icu.util.Calendar
import android.view.*
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*

// 추가로 만들어야 하는 내용
// scroll event 를 이용해서 달력 월이동
// 일 눌렸을때 하는 역할(calendar 의 주기능 추가 할예정)
// 통계와 더보기 버튼 역할

//viewpager2 를 이용한 recycleview로 변경할 예정

class HomeFragment : Fragment(){

    private lateinit var  sharedViewModel: SharedViewModel
    private lateinit var adapter: CalendarAdapter
    lateinit var calendar: Calendar
    lateinit var root:View

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
        initView(sharedViewModel)
    }



    fun initView(sharedViewModel: SharedViewModel){
        val firstFragmentStateAdapter = FragmentStateAdapter(requireActivity())
        viewpager2.adapter  = firstFragmentStateAdapter
        viewpager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        firstFragmentStateAdapter.apply {
            viewpager2.setCurrentItem(this.firstFragmentPosition+sharedViewModel.pageIndex,false)
        }
    }



}

