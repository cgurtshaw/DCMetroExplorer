package com.gwmobile.metroexplorer.manager

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.gwmobile.metroexplorer.Constants
import com.gwmobile.metroexplorer.MetroStationsAdapter
import com.gwmobile.metroexplorer.model.Station
import com.gwmobile.metroexplorer.model.StationList
import com.koushikdutta.ion.Ion

/**
 * Created by cgurtshaw on 9/25/2017.
 */
object FetchMetroStationsManager {

    fun loadMetroStationList (context: Context){
        FetchMetroStationsFromAPI(context).execute()
    }

    fun getMetroStationList(context: Context): StationList{
        val rawResult = PersistanceManager.getMetroStations(context)
        return Gson().fromJson(rawResult, StationList::class.java)
    }


    class FetchMetroStationsFromAPI(val context: Context) : AsyncTask<Void, Void, Void>() {
        val TAG = FetchMetroStationsFromAPI::class.java.name

        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                Log.d(TAG, "Start doInBackground...")
                val rawResult = Ion.with(context).load(Constants.STATIONS_ENDPOINT)
                        .addHeader("api_key", Constants.WMATA_PRIMARY_KEY).asString().get()
                val stationList = Gson().fromJson(rawResult, StationList::class.java)
                stationList.filter()
                storeMetroStations(stationList)
            } catch (e: Exception) {
                Log.e(TAG, e.message)
            }
            return null
        }

        fun storeMetroStations(stationList: StationList){
            val stationString = Gson().toJson(stationList)
            PersistanceManager.setMetroStations(context, stationString)
        }
    }
}