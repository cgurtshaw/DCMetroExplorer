package com.gwmobile.metroexplorer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair

import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_metro_station.*

class MetroStationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_station)
        setSupportActionBar(game_toolbar)

        val recyclerView = rv_station_list
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        FetchMetroStationsManager.getMetroStationList(this, recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.metro, menu)
        return true
    }
}
