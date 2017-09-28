package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 9/27/2017.
 */
data class StationList(@SerializedName("Stations") var stations: ArrayList<Station>) {
    fun filter() {
        val tempHashMap = HashMap<String,Station>()
        for(station : Station in stations){
            if(null == tempHashMap.get(station.name)){
                tempHashMap.put(station.name, station)
            }
        }
        stations = ArrayList<Station>(tempHashMap.values)
    }
}