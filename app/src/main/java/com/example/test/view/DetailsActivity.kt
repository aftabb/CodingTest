package com.example.test.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.databinding.ActivityDetailsBinding
import com.example.test.db.database.TestDatabase
import com.example.test.models.AQMResponseItem
import com.example.test.utility.CoreUtil
import com.example.test.viewmodels.DetailsViewmodel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.Utils

class DetailsActivity : AppCompatActivity() {
    lateinit var viewmodel: DetailsViewmodel
    lateinit var mBinding: ActivityDetailsBinding
    lateinit var db: TestDatabase
    var cityName: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val bundle = intent
        cityName = bundle.getStringExtra("City")

        viewmodel = ViewModelProvider(this).get(DetailsViewmodel::class.java)

        viewmodel.fetchCityRecords(cityName)

        viewmodel.getCityData().observe(this, Observer {
            //show data to graph
            Log.d("DetailsData", it.toString())
            val listData = ArrayList<Entry>()
            for ((xPos, item) in it.withIndex()) {
                listData.add(Entry(xPos.toFloat(), it[xPos].aqi.toFloat()))
            }
            showDataonChart(listData, it)
        })


    }

    private fun showDataonChart(
        listData: ArrayList<Entry>,
        it: List<AQMResponseItem>
    ) {
        val lineDataSet = LineDataSet(listData, "AQM")
        lineDataSet.color = ContextCompat.getColor(this, CoreUtil.getColorCode(it[0].aqi))
        lineDataSet.valueTextColor =
            ContextCompat.getColor(this, R.color.black)
        lineDataSet.lineWidth = 3F
        lineDataSet.label = cityName
        lineDataSet.color = ContextCompat.getColor(this, CoreUtil.getColorCode(it[0].aqi))
        lineDataSet.setCircleColor(
            ContextCompat.getColor(
                this,
                CoreUtil.getColorCode(it[0].aqi)
            )
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            lineDataSet.setDrawFilled(true)
            val fillGradient =
                ContextCompat.getDrawable(this, CoreUtil.getGredientCode(it[0].aqi))
            lineDataSet.fillDrawable = fillGradient
        }

        val lineData = LineData(lineDataSet)
        mBinding.lineChart.data = lineData
        mBinding.lineChart.invalidate()
    }
}