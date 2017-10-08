/*
package com.gwmobile.metroexplorer

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import com.gwmobile.metroexplorer.model.UserLocation
import org.jetbrains.anko.toast


*/
/**
 * Created by Roberto on 10/4/17.
 *//*


class LocationDetector: Activity(), LocationListener{
    val FINE_LOCATION_REQUEST_CODE = 99
    lateinit var locationManager:LocationManager
    var listener: LocationListener? = null


    fun getGPS():Location{
        startGPS()
       val Userlocation = UserLocation("","")

    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun checkForGPS(){
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val permission = Manifest.permission.ACCESS_FINE_LOCATION

        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(permission),FINE_LOCATION_REQUEST_CODE)
        }else{
            startGPS()
        }
    }

    @SuppressLint("MissingPermission")
    fun startGPS(){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0L,0F,this)
    }

    fun stopGPS(){
        locationManager.removeUpdates(this)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == FINE_LOCATION_REQUEST_CODE) {
            if(grantResults != null && grantResults.count() == 1) {
                startGPS()
            }
            else {
                //ask user to change settings
                toast("user denied location access")
            }
        }
        else {
            toast("unhandled request code")
        }
    }


}*/
