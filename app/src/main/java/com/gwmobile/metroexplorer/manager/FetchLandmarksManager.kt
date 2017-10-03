package com.gwmobile.metroexplorer.manager

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.gwmobile.metroexplorer.Constants
import com.gwmobile.metroexplorer.MetroStationsAdapter
import com.gwmobile.metroexplorer.model.Landmark
import com.gwmobile.metroexplorer.model.LandmarkList
import com.gwmobile.metroexplorer.model.Station
import com.gwmobile.metroexplorer.model.StationList
import com.koushikdutta.ion.Ion

/**
 * Created by cgurtshaw on 10/2/2017.
 */

object FetchLandmarksManager {


    fun getLandmarksNearMetro(longitude: Long, latitude: Long, context: Context, recyclerView: RecyclerView) {
        TODO()
    }

    fun getFavoriteLandmarks(context: Context, recyclerView: RecyclerView): String {
        TODO()
    }

    class FetchLandmarksFromAPI(val context: Context, var recyclerView: RecyclerView) : AsyncTask<Long, Void, LandmarkList>() {
        val TAG = FetchLandmarksFromAPI::class.java.name

        override fun doInBackground(vararg params: Long?): LandmarkList {
            try {
                Log.d(TAG, "Start doInBackground...")
                val endpoint = getYelpSearchEndPoint(params[0],params[1])
                val token = YelpAuthManager.getBearerToken(context)
                if(!token.equals(Constants.EMPTY_STRING)){
                    val rawResult = Ion.with(context).load(endpoint)
                            .addHeader("authorization", token).asString().get()
                    val landmarkList = Gson().fromJson(rawResult, LandmarkList::class.java)
                    return landmarkList
                }else{
                    Log.e(TAG, "Yelp Token is Empty")
                    return LandmarkList(ArrayList<Landmark>())
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                return LandmarkList(ArrayList<Landmark>())
            }
        }

        override fun onPostExecute(result: LandmarkList) {
            Log.d(TAG, "Start onPostExecute...")
            TODO()
            //recyclerView.adapter = MetroStationsAdapter(result, context)
        }

        private fun getYelpSearchEndPoint(longitude: Long?, latitude: Long?) : String {
            var endPoint = StringBuilder(Constants.YELP_SEARCH_ENDPOINT)
            endPoint.append("latidue=")
                    .append(latitude.toString())
                    .append("&longitude=")
                    .append(longitude.toString())
                    .append("&categories=landmarks&radius=1609&sort_by=rating")
            return endPoint.toString()
        }
    }

    class FetchFavoritesFromStorage(val context: Context, var recyclerView: RecyclerView) : AsyncTask<Void, Void, LandmarkList>() {
        val TAG = FetchFavoritesFromStorage::class.java.name

        override fun doInBackground(vararg p0: Void?): LandmarkList {
            try {
                Log.d(TAG, "Start doInBackground...")
                val rawResult = PersistanceManager.getFavoriteLandmarks(context)
                return Gson().fromJson(rawResult, LandmarkList::class.java)
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                return LandmarkList(ArrayList<Landmark>())
            }
        }

        override fun onPostExecute(result: LandmarkList) {
            Log.d(TAG, "Start onPostExecute...")
            TODO()
            //recyclerView.adapter = MetroStationsAdapter(result, context)

        }

    }
}