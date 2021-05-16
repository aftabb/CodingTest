package com.example.test.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.db.database.TestDatabase
import com.example.test.models.AQMResponseItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DetailsViewmodel(val context: Application) : AndroidViewModel(context) {
    val data = MutableLiveData<List<AQMResponseItem>>()

    fun getCityData(): LiveData<List<AQMResponseItem>> {
        return data
    }

    fun fetchCityRecords(cityname: String) {
        val executor: Executor = Executors.newSingleThreadExecutor()
        executor.execute {
            //getting data from database base on city
            val db = TestDatabase.getAppDataBase(context)!!
            val records = db.getRecordDao().getCityRecord(cityname)
            data.postValue(records)

        }
    }
}