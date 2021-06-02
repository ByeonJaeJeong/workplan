package com.jeongs.workplan.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "DayInfo")
class DayInfo(@PrimaryKey(autoGenerate = true) var id: Long?,
              @ColumnInfo(name = "startDate") var startDate :String,
              @ColumnInfo(name = "endDate") var endDate : String,
              @ColumnInfo(name = "countryTime")  var countryTime : String?,
              @ColumnInfo(name = "repeat") var repeat : Boolean,
              @ColumnInfo(name = "place") var place : String?,
              @ColumnInfo(name = "memo") var memo : String?,
              ){
    constructor() : this(null, "2020-02-02","2020-02-02","서울(GMT+9:00)",false, null, null)
}