package com.jeongs.workplan

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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



        val startDayBtn: TextView = findViewById(R.id.start_day)
        val endDayBtn : TextView = findViewById(R.id.end_day)
        val startTimeBtn : TextView = findViewById(R.id.start_time)
        val endTimeBtn : TextView = findViewById(R.id.end_time)


        startDayBtn.setOnClickListener{
            //시작일자
            getDate(startDayBtn, Calendar.getInstance())
        }
        endDayBtn.setOnClickListener {
            //종료일자
            getDate(endDayBtn, Calendar.getInstance())
        }
        startTimeBtn.setOnClickListener{
            //시작시간
            getTime(startTimeBtn)
        }
        endTimeBtn.setOnClickListener {
            //종료시간
            getTime(endTimeBtn)
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
    fun getTime(textView: TextView){
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
    fun getDate(textView: TextView, calendar: Calendar){
        val datePicker  =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            val myFormat = "yyyy.MM.dd(E)" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
            textView.text=sdf.format(calendar.time)
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }
}