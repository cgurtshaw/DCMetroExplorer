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
        return ""
    }

    class FetchMetroStationsTask(val context: Context, var recyclerView: RecyclerView) : AsyncTask<Void, Void, JsonObject>() {
        val TAG = FetchMetroStationsTask::class.java.name

        override fun doInBackground(vararg p0: Void?): JsonObject? {
            try {
                Log.d(TAG, "Start doInBackground...")
                return Ion.with(context).load(Constants.STATIONS_ENDPOINT)
                        .addHeader("api_key", Constants.WMATA_PRIMARY_KEY)
                        .asJsonObject().get()
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                return null
            }
        }

        override fun onPostExecute(result: JsonObject?) {
            Log.d(TAG, "Start onPostExecute...")
            val gson = Gson()
            val stationList = gson.fromJson(result, StationList::class.java)
            Log.d(TAG, "End Parse...")
            recyclerView.adapter = MetroStationsAdapter(stationList)
            Log.d("result", result.toString())
        }

    }
}