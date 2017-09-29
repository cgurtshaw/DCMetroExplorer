package com.gwmobile.metroexplorer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_metro_station.*

class LandmarksActivity : AppCompatActivity() {
    lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit var adapter: LandmarksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)
    }
}
