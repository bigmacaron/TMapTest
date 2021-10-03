package kr.kro.fatcats.model.response


import com.google.gson.annotations.SerializedName

data class Pois(
    @SerializedName("poi")
    val poi: List<Poi>
)