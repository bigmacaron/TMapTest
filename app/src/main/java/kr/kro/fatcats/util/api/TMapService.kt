package kr.kro.fatcats.util.api

import kr.kro.fatcats.util.Constants

object TMapService {
    val client : TMapApi = BaseService.getClient(Constants.TMap.BASE_DOMAIN).create(TMapApi::class.java)
}