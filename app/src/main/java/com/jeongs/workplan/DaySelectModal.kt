package com.jeongs.workplan

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.DateFormat
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jeongs.workplan.db.DayInfo
import com.jeongs.workplan.db.DayInfoDB
import kotlinx.android.synthetic.main.activity_day_select.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class DaySelectModal : AppCompatActivity() {
    var date : String = ""
    private var backpressedTime: Long = 0 //백버튼 시간
    private var dayInfoDb: DayInfoDB? = null //DB선언
     var start_date :Calendar = Calendar.getInstance()
     var end_date :Calendar = Calendar.getInstance()
    val myFormat = "yyyy.MM.dd(E)" // mention the format you need
    val timeFormat = "a hh : mm"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_select)
        resources.configuration.setLocale(Locale.KOREAN)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //기본타이틀 보여줄지 여부
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.x_btn)
        //db연결
        dayInfoDb= DayInfoDB.getInstance(this)

        val intent : Intent = intent
        date = intent.getStringExtra("date").toString()
        val startDayBtn: TextView = findViewById(R.id.start_day)
        val endDayBtn : TextView = findViewById(R.id.end_day)
        val startTimeBtn : TextView = findViewById(R.id.start_time)
        val endTimeBtn : TextView = findViewById(R.id.end_time)
        val timeBtn : TextView = findViewById(R.id.time_edit)

        //초기설정
        //시분초 0으로 초기화
        start_date.set(Calendar.HOUR,0)
        start_date.set(Calendar.MINUTE,0)
        start_date.set(Calendar.SECOND,0)
        start_date.set(Calendar.MILLISECOND,0)
        //시분초 0으로 초기화
        end_date.set(Calendar.HOUR,0)
        end_date.set(Calendar.MINUTE,0)
        end_date.set(Calendar.SECOND,0)
        end_date.set(Calendar.MILLISECOND,0)
        //타입변환

        val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
        val time_sdf  =SimpleDateFormat(timeFormat, Locale.KOREAN)
        //시작 , 종료  -연,월,일,시간,분 기본값
        startTimeBtn.text = time_sdf.format(start_date.time)
        endTimeBtn.text = time_sdf.format(end_date.time)
        startDayBtn.text=date
        endDayBtn.text=date

        startDayBtn.setOnClickListener{
            //시작일자
            start_date=getDate(startDayBtn, start_date)
        }
        endDayBtn.setOnClickListener {
            //종료일자
            end_date=getDate(endDayBtn, end_date)
        }
        startTimeBtn.setOnClickListener{
            //시작시간
           start_date= getTime(startTimeBtn, start_date)
        }
        endTimeBtn.setOnClickListener {
            //종료시간
            end_date=getTime(endTimeBtn, end_date)
        }
        timeBtn.setOnClickListener {
            //시간설정
            getTimeSetting(timeBtn)
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                //취소 하는 메소드
                super.onBackPressed()
            }
            R.id.menu_check_btn -> {

                val sdf= SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val end_calendar = Calendar.getInstance()
                var dayInfo= DayInfo(0,sdf.format(start_date.time).toString(),sdf.format(end_date.time).toString(), time_edit.text.toString(),false,place_edit.text.toString(),memo_edit.text.toString())

                val r = Runnable {
                    val insert = dayInfoDb?.dayInfoDao()?.insert(dayInfo)
                    Log.e("dayList","반환값"+insert.toString())
                    Log.e("dayList","저장완료")
                    val intent = Intent(baseContext, MainActivity::class.java)
                    Log.e("tag", date.toString())
                    intent.putExtra("date", date)
                    Log.e("dayList","activity이동")
                    startActivity(intent)
                }
                val thread= Thread(r)
                thread.start()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    //
    private fun getTime(textView: TextView, calendar: Calendar) : Calendar{
        val timeSetListener = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            calendar.set(Calendar.HOUR, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)
            val sdf = SimpleDateFormat(timeFormat, Locale.KOREAN)
            textView.text = sdf.format(calendar.time)
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false)
        timeSetListener.show()
        return calendar
     }
    private fun getDate(textView: TextView, calendar: Calendar): Calendar {
        val datePicker  =  DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
            //데이터 입력
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            textView.text = sdf.format(calendar.time)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePicker.show()
        return calendar
    }
    //커스텀 다이얼로그 생성
    //메뉴바 형식 리턴->TextView로 데이터값 이동
    private fun getTimeSetting(textView: TextView){
        val items = arrayOf("서울(GMT+9)", "미국(GMT-4)", "중국(GMT+8)")
        val builder = AlertDialog.Builder(this)
                .setTitle("시간설정")
                .setItems(items){ dialog, which->
                    textView.text="${items[which]}"
                }.show()
    }


    /*override fun onBackPressed() {
        val toast= Toast.makeText(baseContext, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
        if (System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            toast.show()
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            moveTaskToBack(true);
            finishAndRemoveTask();
            toast.cancel()
        }
    }*/
}