package com.example.geotask.ui.routeMap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geotask.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.geotask.databinding.ActivityRouteMapBinding
import com.example.geotask.ui.PointType

class RouteMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityRouteMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouteMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val firstPoint = LatLng(-34.0, 151.0)
        val secondPoint = LatLng(-34.0, 149.0)

        mMap.addMarker(MarkerOptions().position(firstPoint).title(intent.getStringExtra(PointType.STARTPOINT.name)))
        mMap.addMarker(MarkerOptions().position(secondPoint).title(intent.getStringExtra(PointType.ENDPOINT.name)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(firstPoint))
    }
}