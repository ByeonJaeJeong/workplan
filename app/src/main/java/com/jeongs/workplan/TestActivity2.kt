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

//내일 패치 내용
//버튼 동작 함수로 만들어서 중복사용 줄이기
//(버튼 ,Calendar) 받기
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


        var cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        //요일 한글로 변경
        fun day_of_the_week(number: Int):String{
            when(number){
                1->return "일"
                2->return "월"
                3->return "화"
                4->return "수"
                5->return "목"
                6->return "금"
                else->return "토"
            }
        }

        startDayBtn.setOnClickListener{
            val datePicker  =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val myFormat = "yyyy.MM.dd(E)" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)

                startDayBtn.text=sdf.format(cal.time)
            },year,month,day)
            datePicker.show()
        }
        endDayBtn.setOnClickListener {
            //종료일자
            val datePicker  =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH,month)
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val myFormat = "yyyy.MM.dd(E)" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
                endDayBtn.text=sdf.format(cal.time)
            },year,month,day)
            datePicker.show()

        }
        startTimeBtn.setOnClickListener{
            //시작시간
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR,0)
            cal.set(Calendar.MINUTE,0)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR,hour)
                calendar.set(Calendar.MINUTE,minute)
                val myFormat = "a hh:mm"
                val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
               startTimeBtn.text= sdf.format(calendar.time)
            }
            TimePickerDialog(it.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        endTimeBtn.setOnClickListener {
            //종료시간
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR,0)
            cal.set(Calendar.MINUTE,0)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR,hour)
                calendar.set(Calendar.MINUTE,minute)
                val myFormat = "a hh:mm"
                val sdf = SimpleDateFormat(myFormat, Locale.KOREAN)
                endTimeBtn.text= sdf.format(calendar.time)
            }
            TimePickerDialog(it.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
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
}