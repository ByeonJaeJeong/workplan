package com.jeongs.workplan.ui.home
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.MainActivity
import com.jeongs.workplan.R
import kotlinx.android.synthetic.main.fragment_home_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var  currentDate : Date

    lateinit var calendar_year_month_text : TextView
    lateinit var calendar_view : RecyclerView
    lateinit var calendar_layout : LinearLayout
    lateinit var calendarAdapter: CalendarAdapter

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_view , container , false)
        initView(view)
        return view
    }

    fun initView(view: View){
        pageIndex -= (Int.MAX_VALUE / 2)
        calendar_year_month_text = view.select_date
        calendar_view = view.calendar
        calendar_layout= view.calendar_layout
        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        //포멧 적용
        var datetime: String = SimpleDateFormat(
                mContext.getString(R.string.calendar_year_month_format),
                Locale.KOREA
        ).format(date.time)
        calendar_year_month_text.setText(datetime)
        calendarAdapter= CalendarAdapter(view.context,calendar_layout,date)
        calendar_view.adapter=calendarAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}