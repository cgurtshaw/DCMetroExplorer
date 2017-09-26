package com.gwmobile.metroexplorer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Action when Closest station Btn is press
        btn_closest_station.setOnClickListener{
            val intent = Intent(this,MetroStationActivity::class.java)
            startActivity(intent)
        }

        //Action when Favotite Landmark Btn is press
        btn_view_favorites.setOnClickListener{
            val intent = Intent(this,LandmarksActivity::class.java)
            startActivity(intent)
        }

        //Action when Select a station Btn is press
        btn_view_stations.setOnClickListener{
            val intent = Intent(this,MetroStationActivity::class.java)
            startActivity(intent)
        }

    }
}
