package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 10/2/2017.
 */
data class LandmarkAddress(@SerializedName("display_address") val address: ArrayList<String>)