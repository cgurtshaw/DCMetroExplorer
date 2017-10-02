package com.gwmobile.metroexplorer.manager

import android.content.Context
import android.util.Log
import com.gwmobile.metroexplorer.Constants
import com.koushikdutta.ion.Ion

/**
 * Created by cgurtshaw on 10/1/2017.
 */

object YelpAuthManager{
    val TAG = YelpAuthManager::class.java.name

    fun getBearerToken (context: Context) : String{
        if(PersistanceManager.hasYelpToken(context)){
            return getTokenFromStorage(context)
        }else {
            return getTokenFromAPI(context)
        }
    }

    private fun getTokenFromStorage(context : Context): String{
       return PersistanceManager.getYelpToken(context)
    }

    private fun getTokenFromAPI(context: Context): String{
        try {
            val rawResult = Ion.with(context)
                    .load(Constants.YELP_TOKEN_ENDPOINT)
                    .setBodyParameter("grant_type", "client_credentials")
                    .setBodyParameter("client_id", Constants.YELP_CLIENT_ID)
                    .setBodyParameter("client_secret", Constants.YELP_SECRET)
                    .asJsonObject()
            val token = rawResult.get().get("access_token").toString()
            storeToken(context, token)
            return token
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            return Constants.EMPTY_STRING
        }
    }

    private fun storeToken(context: Context, token: String){
        PersistanceManager.setYelpToken(context, token)
    }
}