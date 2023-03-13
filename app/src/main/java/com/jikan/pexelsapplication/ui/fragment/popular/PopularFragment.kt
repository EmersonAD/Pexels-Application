package com.jikan.pexelsapplication.ui.fragment.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.databinding.FragmentPopularBinding
import com.jikan.pexelsapplication.ui.fragment.adapter.photoadapter.PhotoAdapter
import com.jikan.pexelsapplication.ui.fragment.main.MainFragmentDirections
import com.jikan.pexelsapplication.ui.fragment.popular.viewmodel.PopularViewModel
import com.jikan.pexelsapplication.util.animationCancel
import com.jikan.pexelsapplication.util.pulseAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var photoAdapter: PhotoAdapter
    private val viewModel: PopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        fetchWallpapers()
        observerLoadState()
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter(::detail)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        with(binding.recyclerViewPopular) {
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun fetchWallpapers() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularWallpapers().collectLatest { pagingData ->
                    photoAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun observerLoadState() {
        lifecycleScope.launch {
            photoAdapter.loadStateFlow.collectLatest { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        binding.imagePulseAnimation.pulseAnimation()
                    }
                    is LoadState.NotLoading -> {
                        binding.imagePulseAnimation.animationCancel()
                    }
                    is LoadState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Try again later!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun detail(photo: PhotoDomain) {
        val data = arrayOf(photo.srcDomain.original, photo.description)
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }
}