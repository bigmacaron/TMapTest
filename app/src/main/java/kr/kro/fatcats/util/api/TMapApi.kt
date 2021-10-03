package kr.kro.fatcats.util.api

import kr.kro.fatcats.model.response.SearchPoiInfo
import kr.kro.fatcats.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMapApi {

    @GET("/tmap/pois")
    suspend fun getSearchLocation(
        @Query("appKey") appKey:String = Constants.API_KEY,
        @Query("version") version:String = "1",
        @Query("count") count : String = "20",
        @Query("searchKeyword") searchKeyword : String
    ): Response<SearchPoiInfo?>
}