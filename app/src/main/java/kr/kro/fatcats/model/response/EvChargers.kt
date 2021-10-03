package kr.kro.fatcats.model.response


import com.google.gson.annotations.SerializedName

data class EvChargers(
    @SerializedName("evCharger")
    val evCharger: List<Any>?
)