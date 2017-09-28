package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 9/27/2017.
 */
data class StationList(@SerializedName("stations") val stations: ArrayList<Station>)