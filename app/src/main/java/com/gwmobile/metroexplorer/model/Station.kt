package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 9/27/2017.
 */
data class Station(@SerializedName("Name") val name: String,
                   @SerializedName("Lat") val latitude: Double,
                   @SerializedName("Lon") val longitude: Double,
                   @SerializedName("LineCode1") val line1: String,
                   @SerializedName("LineCode2") val line2: String,
                   @SerializedName("LineCode3") val line3: String,
                   @SerializedName("LineCode4") val line4: String,
                   @SerializedName("Address") val address: StationAddress,
                   var lines : HashSet<String>)