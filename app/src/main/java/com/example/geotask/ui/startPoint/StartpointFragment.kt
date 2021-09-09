package com.example.geotask.ui.startPoint

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.geotask.databinding.FragmentStartpointBinding
import com.example.geotask.ui.common.TextChangeListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class StartpointFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var adapter = SearchListAdapter()
    private lateinit var binding: FragmentStartpointBinding
    var isClick = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartpointBinding.inflate(inflater)
        config()
        initMapView(savedInstanceState)
        return binding.root
    }

    private fun initMapView(savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        binding.mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun config() {
        binding.searchEditText.addTextChangedListener(TextChangeListener {
            if (!isClick) {
                search(binding.searchEditText.text.toString())
            }
        })

        binding.recyclerView.adapter = adapter
        adapter.onItemClicked = { address ->
            selectAddress(address)
        }
    }

    private fun selectAddress(address: Address) {
        mMap.clear()
        binding.recyclerView.isVisible = false
        binding.mapView.isVisible = true
        isClick = true
        binding.searchEditText.setText(address.countryName)
        val position = LatLng(address.latitude, address.longitude)
        mMap.addMarker(MarkerOptions().position(position))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        isClick = false
    }

    private fun displayList() {
        binding.mapView.isVisible = adapter.itemCount == 0
        binding.recyclerView.isVisible = adapter.itemCount > 0
    }

    private fun search(text: String) {
        if (text.isNotEmpty()) {
            adapter.addresses = Geocoder(requireContext()).getFromLocationName(text, 3)
            binding.recyclerView.adapter = adapter
        }
        displayList()
    }
}