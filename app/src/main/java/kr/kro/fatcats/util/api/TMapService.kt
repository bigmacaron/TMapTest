package kr.kro.fatcats.util.api

import kr.kro.fatcats.util.Constants

object TMapService {
    val client : TMapApi = BaseService.getClient(Constants.BASE_DOMAIN).create(TMapApi::class.java)
}