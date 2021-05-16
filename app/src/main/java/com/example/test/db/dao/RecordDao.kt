package com.example.test.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.test.models.AQMResponseItem

@Dao
interface RecordDao {
    @Query("Select * from AQMResponseItem")
    fun getAllRecords(): List<AQMResponseItem>

    @Query("SELECT * FROM aqmresponseitem WHERE city = :cityName  ORDER BY id DESC LIMIT 10")
    fun getCityRecord(cityName: String): List<AQMResponseItem>


    @Insert
    fun addRecord(recordEntiry: AQMResponseItem)

    @Insert
    fun addRecords(records: List<AQMResponseItem>)
}