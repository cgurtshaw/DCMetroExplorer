package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 10/2/2017.
 */

data class LandmarkCoordinates(@SerializedName("latitude") val latitude: Double,
                    @SerializedName("longitude") val longitude: Double)