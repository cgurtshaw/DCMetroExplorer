package com.gwmobile.metroexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_metro_station.*

class MetroStationActivity : AppCompatActivity() {
    lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit var adapter: MetroStationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metro_station)
        //set the layout manager of the RecyclerView to a StaggeredGridLayoutManager
        //use to create two types of vertically staggered grids
        //A span count of 1 makes this a list rather than a grid
        staggeredLayoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        list.layoutManager = staggeredLayoutManager
        adapter = MetroStationAdapter(this)
        list.adapter = adapter
    }
}
