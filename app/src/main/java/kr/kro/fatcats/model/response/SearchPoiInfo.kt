package kr.kro.fatcats.model.response


import com.google.gson.annotations.SerializedName

data class SearchPoiInfo(
    @SerializedName("searchPoiInfo")
    val searchPoiInfo: SearchPoiInfoX
)