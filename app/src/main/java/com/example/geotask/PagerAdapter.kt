package com.example.geotask

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    enum class Tabs {
        STARTPOINT,
        ENDPOINT
    }

    override fun getItemCount(): Int = Tabs.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            Tabs.STARTPOINT.ordinal -> StartpointFragment()
            Tabs.ENDPOINT.ordinal -> EndpointFragment()
            else -> Fragment()
        }
    }

}