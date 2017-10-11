package com.gwmobile.metroexplorer.manager

/**
 * Created by cgurtshaw on 10/10/2017.
 */

import android.content.Context
import android.location.Location
import com.google.android.gms.location.*
import java.util.*
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import kotlin.concurrent.timerTask


class LocationDetector(val context : Context) {
    var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var locationListener: LocationListener? = null

    init {
        fusedLocationClient = FusedLocationProviderClient(context)
    }

    enum class FailureReason {
        TIMEOUT,
        NO_PERMISSION
    }

    interface LocationListener {
        fun locationFound(location: Location)
        fun locationNotFound(reason: FailureReason)
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 0L
    }

    private fun hasPermissions(): Boolean {
        val permissionResult = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)

        return permissionResult == PackageManager.PERMISSION_GRANTED
    }

    private fun createLocationCallBack(): LocationCallback{
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                //stop location updates
                fusedLocationClient.removeLocationUpdates(this)

                //fire callback with location
                locationListener?.locationFound(locationResult.locations.first())
            }
        }
        return locationCallback
    }

    fun detectLocation(){
        createLocationRequest()
        if(hasPermissions()){
            val locationCallback = createLocationCallBack()
            val timer = Timer()
            timer.schedule(timerTask {
                fusedLocationClient?.removeLocationUpdates(locationCallback)
                locationListener?.locationNotFound(FailureReason.TIMEOUT)
            }, 100*1000)

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        } else{
            locationListener?.locationNotFound(FailureReason.NO_PERMISSION)
        }
    }
}