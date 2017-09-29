package com.gwmobile.metroexplorer.model

import com.google.gson.annotations.SerializedName

/**
 * Created by cgurtshaw on 9/28/2017.
 */
data class StationAddress(@SerializedName("Street") val street: String,
                      @SerializedName("City") val city: String,
                      @SerializedName("State") val state: String,
                      @SerializedName("Zip") val zip: String)
