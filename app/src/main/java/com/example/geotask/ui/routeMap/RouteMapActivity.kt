package com.example.geotask.ui.routeMap

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geotask.R

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.geotask.databinding.ActivityRouteMapBinding
import com.example.geotask.network.EntryRequest
import com.example.geotask.network.RetrofitServices

class RouteMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityRouteMapBinding
    lateinit var routesService: RetrofitServices

    companion object {
        private const val point1: String = "STARTPOINT"
        private const val point2: String = "ENDPOINT"
        fun newIntent(
            context: Context,
            startPoint: String?,
            endPoint: String?
        ): Intent {
            val intent = Intent(context, RouteMapActivity::class.java)
            intent.putExtra(point1, startPoint)
            intent.putExtra(point2, endPoint)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouteMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_layout) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getList()
    }


    private fun getList() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val firstPoint = LatLng(-34.0, 151.0)
        val secondPoint = LatLng(-34.0, 149.0)

        mMap.addMarker(MarkerOptions().position(firstPoint).title(intent.getStringExtra(point1)))
        mMap.addMarker(MarkerOptions().position(secondPoint).title(intent.getStringExtra(point2)))
    }
}