package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 10/2/2017.
 */

data class Landmark(@SerializedName("name") val name: String,
                   @SerializedName("image_url") val imageURL: String,
                   @SerializedName("rating") val rating: Double,
                   @SerializedName("coordinates") val coordinates: LandmarkCoordinates,
                   @SerializedName("location") val address : LandmarkAddress)