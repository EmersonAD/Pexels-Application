package com.jikan.pexelsapplication.ui.fragment.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    container: FragmentActivity,
    private val fragmentList: List<Fragment>
) : FragmentStateAdapter(container) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}