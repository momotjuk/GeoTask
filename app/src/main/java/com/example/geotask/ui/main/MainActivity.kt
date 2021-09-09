package com.example.geotask.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.geotask.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityMainBinding

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
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
    }

}