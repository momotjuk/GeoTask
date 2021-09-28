package com.example.geotask.ui.selectPoint

import android.app.Activity
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.geotask.databinding.FragmentSelectpointBinding
import com.example.geotask.ui.PointType
import com.example.geotask.ui.common.TextChangeListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext
import com.example.geotask.ui.main.MainActivity as MainActivity1

class SelectPointFragment(private val type: PointType) : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var adapter = SearchListAdapter()
    private lateinit var binding: FragmentSelectpointBinding
    var isClick = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectpointBinding.inflate(inflater)
        config()
        initMapView(savedInstanceState)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
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
                runBlocking {
                    search(binding.searchEditText.text.toString())
                }
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
        (activity as MainActivity1).setPoint(type, address.countryName)
        hideKeyboard()
        val position = LatLng(address.latitude, address.longitude)
        mMap.addMarker(MarkerOptions().position(position))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position))
        isClick = false
    }

    private fun displayList() {
        binding.mapView.isVisible = adapter.itemCount == 0
        binding.recyclerView.isVisible = adapter.itemCount > 0
    }

    private suspend fun search(text: String) = coroutineScope {
        launch {
            if (text.isNotEmpty()) {
                adapter.addresses = Geocoder(requireContext()).getFromLocationName(text, 2)
                binding.recyclerView.adapter = adapter
            }
            displayList()
        }
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}