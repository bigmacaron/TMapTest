package kr.kro.fatcats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kr.kro.fatcats.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() , OnMapReadyCallback {



    private lateinit var binding : ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpGoogleMap()
    }

    private fun setUpGoogleMap(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {

    }

    companion object{
        const val SEARCH_RESULT_EXTRA_KEY : String =""
    }
}