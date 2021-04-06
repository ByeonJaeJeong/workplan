package com.jeongs.workplan.db

import java.time.Month
import java.time.MonthDay
import java.time.Year

data class calendarDAO(var year: Year , var month: Month , var day: MonthDay)