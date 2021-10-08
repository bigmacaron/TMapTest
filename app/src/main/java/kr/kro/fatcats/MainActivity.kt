package kr.kro.fatcats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import kr.kro.fatcats.databinding.ActivityMainBinding
import kr.kro.fatcats.model.AddressDataList
import kr.kro.fatcats.model.LocationLatLnd
import kr.kro.fatcats.model.response.Poi
import kr.kro.fatcats.model.response.SearchPoiInfo
import kr.kro.fatcats.util.api.TMapApi
import kr.kro.fatcats.util.api.TMapService
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() , CoroutineScope {


    private val job = Job()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: SearchResultRecyclerViewAdapter
    private val linearLayoutManager = LinearLayoutManager(this) // 무한스크롤용

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setClick()
    }

    private fun initView() = with(binding) {
        setAdapter()
        tvSearchResult.isVisible = false
        recyclerView.adapter = mAdapter
    }

    private fun setAdapter() = with(binding) {
        mAdapter = SearchResultRecyclerViewAdapter()
        recyclerView.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            layoutManager = linearLayoutManager
            adapter = mAdapter
        }
    }

    private fun setClick() = with(binding){
        btnSearch.setOnClickListener {
            loadSearchData(etSearchBarInputView.text.toString())
            Log.e("checkKeyword","${etSearchBarInputView.text}")
        }
    }

    private fun loadSearchData(keyWord : String) {
        launch(coroutineContext) {
            try {
                withContext(Dispatchers.IO) {
                    TMapService.client.getSearchLocation(
                        searchKeyword = keyWord
                    ).let { response ->
                        if(response.isSuccessful){
                           withContext(Dispatchers.Main){
                               Log.e("response", response.toString())
                               Log.e("responseBody", response.body().toString())
                               response.body()?.let { SearchPoiInfo ->
                                   val item = SearchPoiInfo.searchPoiInfo.pois.poi.map {
                                        AddressDataList(
                                           name = it.name?:"빌딩명 없음",
                                           fullAddress = makeMainAddress(it),
                                            locationLatLng = LocationLatLnd(
                                               it.noorLat.toFloat(),
                                                it.noorLon.toFloat()
                                            )
                                       )
                                   }
                                   mAdapter.additem(item){
                                       startActivity(
                                           Intent(this@MainActivity,MapActivity::class.java)
                                       )
                                   }

                               }
                           }
                        }else{
                            Log.e("responseFail","${response.code()}")
                        }
                    }

                }
            }catch (e : Exception){

            }
        }
    }

    private fun makeMainAddress(poi: Poi): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    (poi.firstNo?.trim() ?: "") + " " +
                    poi.secondNo?.trim()
        }


}