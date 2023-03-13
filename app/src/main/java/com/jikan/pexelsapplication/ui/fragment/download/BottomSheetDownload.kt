package com.jikan.pexelsapplication.ui.fragment.download

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import com.jikan.pexelsapplication.R
import com.jikan.pexelsapplication.databinding.BottomSheetDownloadBinding
import com.jikan.pexelsapplication.framework.downloader.AndroidDownloader
import okio.IOException

class BottomSheetDownload(
    private val url: String,
    private val description: String,
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetDownloadBinding
    private lateinit var downloader: AndroidDownloader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetDownloadBinding.inflate(inflater, container, false)
        initButtons()
        return binding.root
    }

    private fun initButtons() {
        binding.run {
            downLoadFromNet.setOnClickListener { downloadImageFromNet(url, description) }
            setAsBackground.setOnClickListener { setAsBackground(BACKGROUND) }
            setAsLockscreen.setOnClickListener { setAsBackground(LOCKSCREEN) }
        }
    }

    private fun downloadImageFromNet(url: String, description: String) {
        downloader = AndroidDownloader(requireContext())
        downloader.downloadFile(url, description)
    }

    private fun setAsBackground(lockOrBackground: Int) {
        try {
            val wallpaperManager = WallpaperManager.getInstance(context)
            val image = activity?.findViewById<ShapeableImageView>(R.id.download_image)

            if (image?.drawable == null) {
                Toast.makeText(context, "Wait to download", Toast.LENGTH_LONG).show()
            } else {
                val bitmap = (image.drawable as BitmapDrawable).bitmap
                wallpaperManager.setBitmap(bitmap, null, true, lockOrBackground)
                Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val BACKGROUND = 1
        private const val LOCKSCREEN = 2
    }
}