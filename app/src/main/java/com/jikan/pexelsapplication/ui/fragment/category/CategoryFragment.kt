package com.jikan.pexelsapplication.ui.fragment.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jikan.pexelsapplication.R
import com.jikan.pexelsapplication.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}