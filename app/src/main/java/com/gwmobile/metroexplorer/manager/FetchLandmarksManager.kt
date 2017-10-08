package com.gwmobile.metroexplorer.manager

import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.gwmobile.metroexplorer.Constants
import com.gwmobile.metroexplorer.LandmarksAdapter
import com.gwmobile.metroexplorer.MetroStationsAdapter
import com.gwmobile.metroexplorer.model.Landmark
import com.gwmobile.metroexplorer.model.LandmarkList
import com.koushikdutta.ion.Ion

/**
 * Created by cgurtshaw on 10/2/2017.
 */

object FetchLandmarksManager {


    fun getLandmarksNearMetro(longitude: Double, latitude: Double, context: Context, recyclerView: RecyclerView) {
        FetchLandmarksFromAPI(context, recyclerView).execute(longitude, latitude)
    }

    fun getFavoriteLandmarks(context: Context, recyclerView: RecyclerView) {
        FetchFavoritesFromStorage(context, recyclerView).execute()
    }

    class FetchLandmarksFromAPI(val context: Context, var recyclerView: RecyclerView) : AsyncTask<Double, Void, LandmarkList>() {
        val TAG = FetchLandmarksFromAPI::class.java.name

        override fun doInBackground(vararg params: Double?): LandmarkList {
            try {
                Log.d(TAG, "Start doInBackground...")
                val token = YelpAuthManager.getBearerToken(context)
                if(!token.equals(Constants.EMPTY_STRING)){
                    val rawResult = Ion.with(context).load(Constants.YELP_SEARCH_ENDPOINT)
                            .addHeader("Authorization", "Bearer $token")
                            .addQuery("longitude", params[0].toString())
                            .addQuery("latitude", params[1].toString())
                            .addQuery("categories", "landmarks")
                            .addQuery("radius", "1609")
                            .addQuery("sort_by", "rating")
                            .asString().get()
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
            recyclerView.adapter = LandmarksAdapter(result, context)
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