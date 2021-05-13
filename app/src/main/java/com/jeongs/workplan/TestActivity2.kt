package com.jeongs.workplan

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlinx.android.synthetic.main.activity_test2.*
import java.text.SimpleDateFormat
import java.util.*


class TestActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //기본타이틀 보여줄지 여부
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.x_btn)
        //supportActionBar?.setDisplayShowHomeEnabled(true)

        val intent : Intent = getIntent()
        var get_year = intent.getIntExtra("year",0)
        var get_month = intent.getIntExtra("month",0)
        var get_day = intent.getIntExtra("day",0)
        val get_calendar : Calendar = Calendar.getInstance()
        get_calendar.set(Calendar.YEAR,get_year)
        get_calendar.set(Calendar.MONTH,get_month-1)
        get_calendar.set(Calendar.DAY_OF_MONTH,get_day)


        val startDayBtn: TextView = findViewById(R.id.start_day)
        val endDayBtn : TextView = findViewById(R.id.end_day)
        val startTimeBtn : TextView = findViewById(R.id.start_time)
        val endTimeBtn : TextView = findViewById(R.id.end_time)
        val timeBtn : TextView = findViewById(R.id.time_edit)

        //초기설정
        //시작일자
        val myFormat = "yyyy.MM.dd(E)" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
        startDayBtn.text=sdf.format(get_calendar.time)
        //종료일자
        endDayBtn.text=sdf.format(get_calendar.time)

        startDayBtn.setOnClickListener{
            //시작일자
            getDate(startDayBtn, get_calendar)
        }
        endDayBtn.setOnClickListener {
            //종료일자
            getDate(endDayBtn, get_calendar)
        }
        startTimeBtn.setOnClickListener{
            //시작시간
            getTime(startTimeBtn)
        }
        endTimeBtn.setOnClickListener {
            //종료시간
            getTime(endTimeBtn)
        }
        timeBtn.setOnClickListener {
            //시간설정
            getTimeSetting(timeBtn)
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                onBackPressed()
            }
            R.id.menu_check_btn->{
                onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    //
    private fun getTime(textView: TextView){
        val timeSetListener = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR,hour)
            calendar.set(Calendar.MINUTE,minute)
            val myFormat = "a hh:mm"
            val sdf = SimpleDateFormat(myFormat, Locale.KOREA)
            textView.text= sdf.format(calendar.time)
        },0,0,false)
        timeSetListener.show()
    }
    private fun getDate(textView: TextView, calendar: Calendar){
        val datePicker  =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val myFormat = "yyyy.MM.dd(E)" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
            textView.text=sdf.format(calendar.time)
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
    //커스텀 다이얼로그 생성
    //메뉴바 형식 리턴->TextView로 데이터값 이동
    private fun getTimeSetting(textView: TextView){
        val items = arrayOf("서울(GMT+9)","미국(GMT-4)","중국(GMT+8)")
        val builder = AlertDialog.Builder(this)
                .setTitle("시간설정")
                .setItems(items){ dialog, which->
                    textView.text="${items[which]}"
                }.show()
    }
}