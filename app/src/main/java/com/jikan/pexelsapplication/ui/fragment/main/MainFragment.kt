package com.jikan.pexelsapplication.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.jikan.pexelsapplication.R
import com.jikan.pexelsapplication.databinding.FragmentMainBinding
import com.jikan.pexelsapplication.ui.fragment.category.CategoryFragment
import com.jikan.pexelsapplication.ui.fragment.collections.CollectionsFragment
import com.jikan.pexelsapplication.ui.fragment.pageradapter.ViewPagerAdapter
import com.jikan.pexelsapplication.ui.fragment.popular.PopularFragment


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val tabTitles = listOf("Popular", "Collections", "Category")
    private val fragments = listOf(PopularFragment(), CollectionsFragment(), CategoryFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        initTabLayout()
        travelToGallery()
    }

    private fun initViewPager() {
        val pageAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
        binding.run {
            viewPager.adapter = pageAdapter
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun travelToGallery() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_galleryFragment)
        }
    }
}
