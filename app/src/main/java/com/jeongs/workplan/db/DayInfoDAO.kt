package com.jeongs.workplan.db

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


}