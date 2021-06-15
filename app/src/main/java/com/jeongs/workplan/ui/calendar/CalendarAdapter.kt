package com.jeongs.workplan.ui.calendar

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.jeongs.workplan.R
import com.jeongs.workplan.DaySelectModal
import kotlinx.android.synthetic.main.item_day.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
//참고  사이트 https://furang-note.tistory.com/29
//높이를 구하는데 필요한 LinearLayout 과 FurangCalendar를 사용할때 필요한 data를 받음
class CalendarAdapter(val context: Context, val calendarLayout: LinearLayout, val date: Date,val parentView: View)
    : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    var dataList: ArrayList<Int> = arrayListOf()
    // FurangCalendar를 이용한 날짜 리스트 세팅
    var furangCalendar: FurangCalendar = FurangCalendar(date)
    init {
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
            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead -1

            //날짜 표시
            itemCalendarDateText.setText((data.toString()))
            //오늘 날짜 처리
            var dateString : String =SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            var calendar = Calendar.getInstance()

            //오늘 날짜 글자크기 굵게
            if( dataList[position] == dateInt){
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            //현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색처리
            if(position < firstDateIndex || position > lastDateIndex){
                itemCalendarDateText.setBackgroundResource(R.drawable.calendar_item_gray)
                //아닌경우 검정색
                itemCalendarDateText.setTextColor(Color.GRAY)
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.ITALIC)
            }else{
                itemCalendarDateText.setTextColor(Color.BLACK)
                //주말 색상변경
                //일요일
                if(position % 7 == 0) {
                    itemCalendarDateText.setTextColor(Color.RED)
                }
                //토요일
                if(position % 7 == 6){
                    itemCalendarDateText.setTextColor(Color.BLUE)
                }
            }
            //이전달
            if(position < firstDateIndex){
                itemView.setOnClickListener {
                    parentView?.findViewById<ViewPager2>(R.id.viewpager2).currentItem = parentView?.findViewById<ViewPager2>(R.id.viewpager2).currentItem-1

                }
            }
            //다음달
            else if(position > lastDateIndex){
                itemView.setOnClickListener {
                    parentView?.findViewById<ViewPager2>(R.id.viewpager2).currentItem = parentView?.findViewById<ViewPager2>(R.id.viewpager2).currentItem+1
                }
            }
            //이번달
            else{
                itemView.setOnClickListener {
                    val eventCalendar = Calendar.getInstance()  //현재날짜
                    eventCalendar.time = date   //date추가
                    eventCalendar.set(Calendar.DAY_OF_MONTH,data) //일자 변경
                    val simpledateformat:SimpleDateFormat= SimpleDateFormat("yyyy.MM.dd(E)", Locale.KOREA) //포멧변경
                    val intent = Intent(it.context,DaySelectModal::class.java)   //이동준비
                    intent.putExtra("date",simpledateformat.format(eventCalendar.time)) //데이터 입력
                    ContextCompat.startActivity(it.context,intent, Bundle.EMPTY)    //이동

                }
            }


        }
    }




}