package com.gwmobile.metroexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.gwmobile.metroexplorer.manager.FetchMetroStationsManager
import kotlinx.android.synthetic.main.activity_metro_station.*

class MetroStationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_station)
        val recyclerView = rv_station_list
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        FetchMetroStationsManager.getMetroStationList(this, recyclerView)
    }
}
