package com.jeongs.workplan.db

import java.time.DayOfWeek
import java.time.Month
import java.time.MonthDay
import java.time.Year

data class CalendarDAO(var year: Int, var month: Int, var day: Int, var  week: Int)