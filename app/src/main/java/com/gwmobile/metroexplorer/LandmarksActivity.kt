package com.gwmobile.metroexplorer

import android.content.Context
import android.content.Intent
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.gwmobile.metroexplorer.manager.FetchLandmarksManager
import com.gwmobile.metroexplorer.manager.FetchMetroStationsManager
import com.gwmobile.metroexplorer.utils.MetroUtil
import kotlinx.android.synthetic.main.activity_landmarks.*
import kotlinx.android.synthetic.main.activity_metro_station.*

class LandmarksActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)
        recyclerView = rv_landmark_list
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //obtain requestType from intent
        val requestType = intent.getStringExtra("requestType")

        when(requestType){
            Constants.LOCATION ->{
                val lon = intent.getDoubleExtra("lon", 0.0)
                val lat = intent.getDoubleExtra("lat", 0.0)
                val metroStation = MetroUtil.getClosestStation(lon, lat, this)
                val metroLon = metroStation?.longitude ?: 0.0
                val metroLat = metroStation?.latitude ?: 0.0
                FetchLandmarksManager.getLandmarksNearMetro(metroLon, metroLat, this, recyclerView)
            }
            Constants.FAVORITE -> fetchFavorites()
        }
    }

    private fun fetchFavorites() {
        FetchLandmarksManager.getFavoriteLandmarks(this, recyclerView)
    }
}
