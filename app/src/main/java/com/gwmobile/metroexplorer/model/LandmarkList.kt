package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 10/2/2017.
 */
data class LandmarkList(@SerializedName("businesses") var landmarks: ArrayList<Landmark>)