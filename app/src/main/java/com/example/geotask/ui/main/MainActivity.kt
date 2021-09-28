package com.example.geotask.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.geotask.ui.routeMap.RouteMapActivity
import com.example.geotask.databinding.ActivityMainBinding
import com.example.geotask.ui.PointType
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private var startPoint: String? = null
    private var endPoint: String? = null

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    fun setPoint(type: PointType, point: String){
        when (type){
            PointType.STARTPOINT -> startPoint = point
            PointType.ENDPOINT -> endPoint = point
        }
    }

    private val tabNames: Array<String> = arrayOf(
        "Start point",
        "End point",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this)
        binding.pager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        binding.actionButton.setOnClickListener {
            startActivity(RouteMapActivity.newIntent(this, startPoint, endPoint))
        }
    }
}