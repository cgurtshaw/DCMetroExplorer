package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 9/27/2017.
 */
data class StationList(@SerializedName("Stations") var stations: ArrayList<Station>) {
    fun filter() {
        var tempHashMap = HashMap<String,Station>()
        for(station : Station in stations){
            val stationLines = listOfNotNull(station.line1, station.line2, station.line3, station.line4)
            if(null == tempHashMap.get(station.name)){
                var lineHash : HashSet<String> = HashSet<String>()
                stationLines.forEach { line: String -> lineHash.add(line) }
                station.lines = lineHash
                tempHashMap.put(station.name, station)
            }else{
                var stationValue = tempHashMap.get(station.name)!!
                stationLines.forEach{line: String -> stationValue.lines.add(line) }
                tempHashMap.put(station.name, stationValue)
            }
        }
        stations = ArrayList<Station>(tempHashMap.values)
    }
}