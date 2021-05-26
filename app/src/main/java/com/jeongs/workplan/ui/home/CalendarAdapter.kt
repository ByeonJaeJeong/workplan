package com.jeongs.workplan.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeongs.workplan.MainActivity
import com.jeongs.workplan.R
import com.jeongs.workplan.R.drawable.calendar_item_gray
import com.jeongs.workplan.TestActivity2
import com.jeongs.workplan.db.CalendarDAO
import kotlinx.android.synthetic.main.fragment_home_view.view.*
import kotlinx.android.synthetic.main.item_day.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
//참고  사이트 https://furang-note.tistory.com/29
//높이를 구하는데 필요한 LinearLayout 과 FurangCalendar를 사용할때 필요한 data를 받음
class CalendarAdapter(val context: Context, val calendarLayout: LinearLayout, val date: Date)
    : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()
    // FurangCalendar를 이용한 날짜 리스트 세팅
    var furangCalendar: FurangCalendar = FurangCalendar(date)
    init {
        Log.e("작동여부","init 실행")
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    interface ItemClick{
        fun onClick(view: View , position: Int)
    }
    var itemClick : ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view =
                LayoutInflater.from(context).inflate(R.layout.item_day, parent, false)
        return CalendarItemHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        //item_day 높이 지정
        val h = (calendarLayout.height / 6)
        holder.itemView.layoutParams.height = h

        holder?.bind(dataList[position], position, context)
        if(itemClick != null){
            holder?.itemView?.setOnClickListener { v->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size



    inner class CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var itemCalendarDateText: TextView = itemView!!.textView_DateNumber

        fun bind(data: Int, position: Int, context: Context) {
            Log.e("작동여부","스타트")
            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead -1

            //날짜 표시
            itemCalendarDateText.setText((data.toString()))
            Log.e("작동여부",data.toString())
            //오늘 날짜 처리
            var dateString : String =SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            var calendar = Calendar.getInstance()

            if( dataList[position] == dateInt){
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }
            //현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if(position < firstDateIndex || position > lastDateIndex){
                itemCalendarDateText.setBackgroundResource(R.drawable.calendar_item_gray)
                //아닌경우 검정색
                itemCalendarDateText.setTextColor(Color.BLACK)
                itemView.setOnClickListener {

                }
            }
            if(position < firstDateIndex){
                itemView.setOnClickListener {
                    Toast.makeText(context,"이전달로 이동",Toast.LENGTH_SHORT).show()

                }
            }
            else if(position > lastDateIndex){
                itemView.setOnClickListener {
                    Toast.makeText(context,"다음달로 이동",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                itemView.setOnClickListener {
                    Toast.makeText(context,"일자 클릭 이벤트 발생",Toast.LENGTH_SHORT).show()
                }
            }
            //일요일
            if(position % 7 == 0) {
                itemCalendarDateText.setTextColor(Color.RED)
            }
            //토요일
            if(position % 7 == 6){
                itemCalendarDateText.setTextColor(Color.BLUE)
            }

        }
    }




}