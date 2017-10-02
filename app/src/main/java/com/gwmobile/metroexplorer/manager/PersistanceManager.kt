package com.gwmobile.metroexplorer.manager

import com.gwmobile.metroexplorer.Constants
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by cgurtshaw on 9/30/2017.
 */

object PersistanceManager{

    private fun getSharedPreferences(context: Context) : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun hasMetroStations(context: Context) : Boolean{
        return getSharedPreferences(context).contains(Constants.METRO_STATION_KEY)
    }

    fun getMetroStations(context: Context) : String{
        return getSharedPreferences(context).getString(Constants.METRO_STATION_KEY, Constants.EMPTY_STRING)
    }

    fun setMetroStations(context: Context, stations : String){
        getSharedPreferences(context).edit().putString(Constants.METRO_STATION_KEY, stations).commit()
    }

    fun  hasYelpToken(context: Context): Boolean {
        return getSharedPreferences(context).contains(Constants.YELP_TOKEN)
    }

    fun  setYelpToken(context: Context, token: String) {
        getSharedPreferences(context).edit().putString(Constants.YELP_TOKEN, token).commit()
    }

    fun getYelpToken(context: Context) : String{
        return getSharedPreferences(context).getString(Constants.YELP_TOKEN, Constants.EMPTY_STRING)
    }


}