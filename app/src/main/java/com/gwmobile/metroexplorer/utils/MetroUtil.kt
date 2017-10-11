package com.gwmobile.metroexplorer.utils

import android.content.Context
import com.gwmobile.metroexplorer.manager.FetchMetroStationsManager
import com.gwmobile.metroexplorer.model.Station

/**
 * Created by cgurtshaw on 10/10/2017.
 */
object MetroUtil{
    fun getClosestStation(lon: Double, lat: Double, context: Context): Station? {
        val stationList = FetchMetroStationsManager.getMetroStationList(context)
        var closestStation : Station? = null
        var closestDistance : Double? = null
        for(station in stationList.stations){
            val tempDist = calculateDistance(lat, lon, station.latitude, station.longitude)
            if(closestDistance == null){
                closestDistance = tempDist
                closestStation = station
            }else{
                if(tempDist < closestDistance){
                    closestDistance = tempDist
                    closestStation = station
                }
            }
        }
        return closestStation
    }

    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = Math.sin(toRadian(lat1)) * Math.sin(toRadian(lat2)) + Math.cos(toRadian(lat1)) * Math.cos(toRadian(lat2)) * Math.cos(toRadian(theta))
        dist = Math.acos(dist)
        dist = toDegree(dist)
        dist *= 60.0 * 1.1515
        return dist
    }

    private fun toRadian(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun toDegree(rad: Double): Double {
        return rad * 180 / Math.PI
    }
}