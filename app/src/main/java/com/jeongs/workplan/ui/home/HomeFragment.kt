
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
import android.view.*
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_home.*

// 추가로 만들어야 하는 내용
// scroll event 를 이용해서 달력 월이동
// 일 눌렸을때 하는 역할(calendar 의 주기능 추가 할예정)
// 통계와 더보기 버튼 역할

//viewpager2 를 이용한 recycleview로 변경할 예정

class HomeFragment : Fragment() , View.OnClickListener{

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



    override fun onClick(v: View?) {
        /*when(v?.id){
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
        }*/
    }


    fun initView(sharedViewModel: SharedViewModel){
        val firstFragmentStateAdapter = FragemntStateAdapter(requireActivity())
        viewpager2.adapter  = firstFragmentStateAdapter
        viewpager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        firstFragmentStateAdapter.apply {
            viewpager2.setCurrentItem(this.firstFragmentPosition+sharedViewModel.pageIndex,false)
        }
    }


}

