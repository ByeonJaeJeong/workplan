package com.jeongs.workplan.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [DayInfo::class], version = 1)
abstract class DayInfoDB : RoomDatabase() {
    abstract  fun dayInfoDao(): DayInfoDAO
    companion object {
        private var INSTANCE: DayInfoDB? = null


        fun getInstance(context: Context): DayInfoDB? {
            if (INSTANCE == null) {
                synchronized(DayInfoDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            DayInfoDB::class.java, "dayinfo.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destoryInstance() {
            INSTANCE = null
        }
    }
}