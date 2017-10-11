package com.gwmobile.metroexplorer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*
import com.gwmobile.metroexplorer.manager.LocationDetector
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast


class MenuActivity : AppCompatActivity(), LocationDetector.LocationListener {
    private val TAG = "MENUACTIVITY"
    private val LOCATION_PERMISSION_REQUEST_CODE = 888
    private var lon : Double = 0.0
    private var lat : Double = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Action when Closest station Btn is press
        btn_closest_station.setOnClickListener{
            val msg = "Lon: $lon Lat: $lat"
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

            val intent = Intent(this@MenuActivity, LandmarksActivity::class.java)
            intent.putExtra("requestType", Constants.LOCATION)
            intent.putExtra("lat", lat)
            intent.putExtra("lon", lon)
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

        requestPermissionsIfNecessary()
        val locationD = LocationDetector(this)
        locationD.locationListener = this
        locationD.detectLocation()

    }

    override fun onResume() {
        super.onResume()
    }


    override fun locationFound(location: Location) {
        showLoading(false)
        lon = location.longitude
        lat = location.latitude
    }

    override fun locationNotFound(reason: LocationDetector.FailureReason) {
        showLoading(false)
        when(reason){
            LocationDetector.FailureReason.TIMEOUT -> Log.e(TAG, "Location timed out")
            LocationDetector.FailureReason.NO_PERMISSION -> Log.e(TAG, "No location permission")
        }

    }

    private fun showLoading(show: Boolean) {
        if(show) {
            progressBar.visibility = ProgressBar.VISIBLE
        }
        else {
            progressBar.visibility = ProgressBar.INVISIBLE
        }
    }

    private fun requestPermissionsIfNecessary() {
        val checkSelfPermission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if(grantResults.isNotEmpty() && grantResults.first() != PackageManager.PERMISSION_GRANTED) {
                requestPermissionsIfNecessary()
            }
        }
    }

}
