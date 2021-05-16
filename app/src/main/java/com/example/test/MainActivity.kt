package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.view.adapters.AqiListAdapter
import com.example.test.databinding.ActivityMainBinding
import com.example.test.db.database.TestDatabase
import com.example.test.models.AQMResponse
import com.example.test.presenter.MainPresenter
import com.example.test.utility.CoreUtil
import com.example.test.view.DetailsActivity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity(), MainPresenter.View, AqiListAdapter.AdapterListner {


    private lateinit var client: OkHttpClient
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: AqiListAdapter
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var db: TestDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        initSocket()
        initAdapter()
    }

    private fun initAdapter() {
        mBinding.cityList.layoutManager = LinearLayoutManager(this)
        adapter = AqiListAdapter(this, ArrayList(), this)

        mBinding.cityList.adapter = adapter

    }

    private fun initSocket() {
        client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(CoreUtil.socketUrl).build()
        presenter = MainPresenter(this)
        client.newWebSocket(request, presenter)
        client.dispatcher().executorService().shutdown()
    }

    override fun onDataReceived(data: String) {
        val gson = Gson()
        val myObject = gson.fromJson(data, AQMResponse::class.java)

        Log.d("Final Data", myObject.toString())
        //save records in database
        db = TestDatabase.getAppDataBase(this)!!
        db.getRecordDao().addRecords(myObject)
        runOnUiThread {
            adapter.addItems(myObject)
        }

    }

    override fun onClick(cityName: String) {
        //starting details activity

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("City", cityName)
        startActivity(intent)
    }
}