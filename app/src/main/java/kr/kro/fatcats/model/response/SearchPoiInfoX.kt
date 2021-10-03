package kr.kro.fatcats.model.response


import com.google.gson.annotations.SerializedName

data class SearchPoiInfoX(
    @SerializedName("count")
    val count: String?,
    @SerializedName("page")
    val page: String?,
    @SerializedName("pois")
    val pois: Pois,
    @SerializedName("totalCount")
    val totalCount: String?
)