package kr.kro.fatcats.model

import com.google.gson.annotations.SerializedName


data class LocationLatLnd(
    @SerializedName("latitude")
    val latitude : Float,
    @SerializedName("longitude")
    val longitude : Float
)


