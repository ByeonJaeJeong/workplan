package com.jeongs.workplan

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeongs.workplan.ui.calendar.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var backpressedTime: Long = 0
    private lateinit var  sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //프래그먼트 연결
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        sharedViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SharedViewModel::class.java)


        if(!intent.getStringExtra("date").equals(null)){
            val date: String? = intent.getStringExtra("date")
            sharedViewModel.year = date?.substring(0, 4)?.toInt() ?: sharedViewModel.year
            sharedViewModel.month = (date?.substring(6, 7)?.toInt())?.minus(1) ?: sharedViewModel.month
        }


    }



    override fun onDestroy() { //앱이 종료되는 시점이 다가올때 호출
        super.onDestroy()
        saveData()
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit() //추가나 수정모드
        val currentDateTime = Calendar.getInstance().time
        var dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
        edit.putString("realtime", dateFormat)
        Log.v("현재시간", dateFormat)
        edit.apply() //값을 저장 완료
    }

    override fun onBackPressed() {
        val toast= Toast.makeText(applicationContext, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
        if (System.currentTimeMillis() > backpressedTime + 2000) {
            backpressedTime = System.currentTimeMillis();
            toast.show()
        } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
            finish()
            toast.cancel()
        }
    }

}