package com.example.test.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.db.dao.RecordDao
import com.example.test.models.AQMResponseItem

@Database(entities = [AQMResponseItem::class], version = 1, exportSchema = false)
abstract class TestDatabase : RoomDatabase() {
    abstract fun getRecordDao(): RecordDao

    companion object {
        var INSTANCE: TestDatabase? = null

        fun getAppDataBase(context: Context): TestDatabase? {
            if (INSTANCE == null) {
                synchronized(TestDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TestDatabase::class.java,
                        "myRecords"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}