package com.gwmobile.metroexplorer

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.gwmobile.metroexplorer.model.Station
import com.gwmobile.metroexplorer.model.StationList
import com.koushikdutta.ion.Ion
import org.json.JSONObject

/**
 * Created by cgurtshaw on 9/25/2017.
 */
object FetchMetroStationsManager {

    fun getMetroStationList (context: Context, recyclerView: RecyclerView) = FetchMetroStationsTask(context, recyclerView).execute()

    fun getClosestMetroStation (longitude: Long, latitude: Long, context: Context) : String{
        TODO()
    }

    class FetchMetroStationsTask(val context: Context, var recyclerView: RecyclerView) : AsyncTask<Void, Void, StationList>() {
        val TAG = FetchMetroStationsTask::class.java.name

        override fun doInBackground(vararg p0: Void?): StationList {
            try {
                Log.d(TAG, "Start doInBackground...")
                val rawResult = Ion.with(context).load(Constants.STATIONS_ENDPOINT)
                        .addHeader("api_key", Constants.WMATA_PRIMARY_KEY).asString().get()
                val stationList = Gson().fromJson(rawResult, StationList::class.java)
                stationList.filter()
                return stationList
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                return StationList(ArrayList<Station>())
            }
        }

        override fun onPostExecute(result: StationList) {
            Log.d(TAG, "Start onPostExecute...")
            recyclerView.adapter = MetroStationsAdapter(result)
        }

    }
}