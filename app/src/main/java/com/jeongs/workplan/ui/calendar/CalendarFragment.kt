
package com.jeongs.workplan.ui.calendar
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.MainActivity
import com.jeongs.workplan.R
import com.jeongs.workplan.db.DayInfo
import com.jeongs.workplan.db.DayInfoDB
import kotlinx.android.synthetic.main.fragment_home_view.view.*
import java.util.*

class CalendarFragment(val parentView: View?) : Fragment() {
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var  currentDate : Date
    private lateinit var  sharedViewModel: SharedViewModel
    lateinit var calendar_year_month_text : TextView
    lateinit var calendar_view : RecyclerView
    lateinit var calendar_layout : LinearLayout
    lateinit var calendarAdapter: CalendarAdapter
    private var sharedate = Calendar.getInstance()
    private var dayInfoDb: DayInfoDB? = null
    companion object{
        var instance : CalendarFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if( context is MainActivity){
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        activity?.run {
            sharedViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
            sharedate.set(Calendar.YEAR,sharedViewModel.year)
            sharedate.set(Calendar.MONTH,sharedViewModel.month)
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_view, container, false)
        initView(view)

        return view
    }
    //캘린더 어뎁터 연결
    fun initView(view: View){
        pageIndex -= (Int.MAX_VALUE / 2)
        calendar_year_month_text = parentView?.findViewById(R.id.select_date)!!
        calendar_view = view.calendar
        calendar_layout= view.calendar_layout
        val date = sharedate.run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        val calendar= Calendar.getInstance()
        calendar.time= date
        //db연결
        dayInfoDb= context?.applicationContext?.let { DayInfoDB.getInstance(it) }
        var dayList = listOf<DayInfo>()
        //DB데이터 불러오는 작업
        val r = Runnable {
            dayList= dayInfoDb?.dayInfoDao()?.selectDate(calendar.get(Calendar.YEAR).toString()+"-"+String.format("%02d",calendar.get(Calendar.MONTH)+1)+"%")!!
            Log.e("dayList",String.format("%02d",calendar.get(Calendar.YEAR))+","+( String.format("%02d",calendar.get(Calendar.MONTH)+1))+","+pageIndex.toString())
            for(day in dayList){
                Log.e("dayList",day.toString())
            }

        }
        val thread= Thread(r)
        thread.start()
        calendarAdapter= CalendarAdapter(view.context,calendar_layout,date,parentView)
        calendar_view.adapter=calendarAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}