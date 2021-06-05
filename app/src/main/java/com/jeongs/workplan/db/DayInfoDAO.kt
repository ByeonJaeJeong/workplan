package com.jeongs.workplan.db

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DayInfoDAO{
    @Query("SELECT * FROM DayInfo")
    fun getAll(): List<DayInfo>
    @Insert
    fun insert(dayInfo : DayInfo)
    @Query("SELECT * FROM DayInfo WHERE startDate Like :date ")
    fun selectDate(date : String ) :List<DayInfo>

}