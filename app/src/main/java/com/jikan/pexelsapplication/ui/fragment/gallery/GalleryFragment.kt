package com.jikan.pexelsapplication.ui.fragment.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.databinding.FragmentGalleryBinding
import com.jikan.pexelsapplication.ui.fragment.adapter.galleryadapter.GalleryAdapter
import com.jikan.pexelsapplication.ui.fragment.gallery.viewmodel.GalleryViewModel
import com.jikan.pexelsapplication.util.CustomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private val galleryViewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        getAllPhotos()
        backButton()
    }

    private fun getAllPhotos() {
        galleryViewModel.state.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is GalleryViewModel.UiState.ShowGallery -> {
                    galleryAdapter.submitList(uiState.photos)
                }
                GalleryViewModel.UiState.EmptyGallery -> {
                    galleryAdapter.submitList(emptyList())
                }
                GalleryViewModel.UiState.Error -> {
                    Toast.makeText(requireContext(), "Deu erro!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initAdapter() {
        galleryAdapter = GalleryAdapter(::detail, ::delete)
        val gridLayout = GridLayoutManager(requireContext(), 3)

        with(binding.galleryRv) {
            layoutManager = gridLayout
            setHasFixedSize(true)
            adapter = galleryAdapter
        }
    }

    private fun detail(photoDomain: PhotoDomain) {
        val data = arrayOf(photoDomain.srcDomain?.original, photoDomain.description)
        findNavController().navigate(
            GalleryFragmentDirections.actionGalleryFragmentToDownloadFragment(data)
        )
    }

    private fun delete(photoDomain: PhotoDomain) {
        val dialog = CustomDialog(photoDomain) { galleryViewModel.delete(photoDomain) }
        dialog.show(childFragmentManager, "Delete Dialog")
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}