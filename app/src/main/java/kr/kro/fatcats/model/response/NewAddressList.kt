package kr.kro.fatcats.model.response


import com.google.gson.annotations.SerializedName

data class NewAddressList(
    @SerializedName("newAddress")
    val newAddress: List<NewAddres>?
)