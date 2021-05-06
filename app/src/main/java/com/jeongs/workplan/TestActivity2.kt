package com.jeongs.workplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TestActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        val exit_Btn = findViewById<Button>(R.id.exit_btn)
        exit_Btn.setOnClickListener {
            onBackPressed()
        }
    }
}