package com.jeongs.workplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_test2.*

class TestActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) //기본타이틀 보여줄지 여부
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.x_btn)
        //supportActionBar?.setDisplayShowHomeEnabled(true)



        val startDayBtn: EditText = findViewById(R.id.start_day)
        val endDayBtn : EditText = findViewById(R.id.end_day)
        val startTimeBtn : EditText = findViewById(R.id.start_time)
        val endTimeBtn : EditText = findViewById(R.id.end_time)

        startDayBtn.setOnClickListener{
            //시작일자
        }
        endDayBtn.setOnClickListener {
            //종료일자
        }
        startTimeBtn.setOnClickListener{
            //시작시간
        }
        endTimeBtn.setOnClickListener {
            //종료시간
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