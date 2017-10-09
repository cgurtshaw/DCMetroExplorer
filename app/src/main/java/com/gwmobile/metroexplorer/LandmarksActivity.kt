package com.gwmobile.metroexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.gwmobile.metroexplorer.manager.FetchLandmarksManager
import com.gwmobile.metroexplorer.manager.FetchMetroStationsManager
import kotlinx.android.synthetic.main.activity_landmarks.*
import kotlinx.android.synthetic.main.activity_metro_station.*

class LandmarksActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)
        val recyclerView = rv_landmark_list
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        FetchLandmarksManager.getLandmarksNearMetro(-77.005086, 38.876588, this, recyclerView)
    }
}
