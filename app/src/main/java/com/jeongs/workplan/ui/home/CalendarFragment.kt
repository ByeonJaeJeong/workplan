package com.jeongs.workplan.ui.home
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
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
import kotlinx.android.synthetic.main.fragment_home_view.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment() : Fragment() {
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var  currentDate : Date
    private lateinit var  sharedViewModel: SharedViewModel
    lateinit var calendar_year_month_text : TextView
    lateinit var calendar_view : RecyclerView
    lateinit var calendar_layout : LinearLayout
    lateinit var calendarAdapter: CalendarAdapter
    private var sharedate = Calendar.getInstance()

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
            sharedViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)
           //pageIndex가 0이면 데이터를 집어넣음
            if(pageIndex == Int.MAX_VALUE /2)
            pageIndex = sharedViewModel.pageIndex + (Int.MAX_VALUE / 2)
        }
    }
    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_view , container , false)
        initView(view)
        val selectDate = view.findViewById<TextView>(R.id.select_date)
        selectDate.setOnClickListener {
            val bottomDialog : MonthSelectDialog = MonthSelectDialog(it.select_date.text.toString())
            activity?.let { bottomDialog.show(it.supportFragmentManager,bottomDialog.tag)}
            fragmentManager?.executePendingTransactions()
            bottomDialog.dialog?.setOnDismissListener(DialogInterface.OnDismissListener() {
                /*pageIndex = sharedViewModel.pageIndex + (Int.MAX_VALUE / 2)
                initView(view)*/
            })
        }
        return view
    }

    fun initView(view: View){
        pageIndex -= (Int.MAX_VALUE / 2)
        calendar_year_month_text = view.select_date
        calendar_view = view.calendar
        calendar_layout= view.calendar_layout
        val date = sharedate.run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        val calendar= Calendar.getInstance()
        calendar.time= date
        Log.e("page","현재 데이터 "+calendar.get(Calendar.YEAR).toString()+"년"+(calendar.get(Calendar.MONTH)+1).toString()+"월")
        sharedViewModel.pageIndex=pageIndex
        //포멧 적용
        var datetime: String = SimpleDateFormat(
                mContext.getString(R.string.calendar_year_month_format),
                Locale.KOREA
        ).format(date.time)
        Log.e("pageIndex",pageIndex.toString())
        calendar_year_month_text.setText(datetime)
        calendarAdapter= CalendarAdapter(view.context,calendar_layout,date)
        calendar_view.adapter=calendarAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}