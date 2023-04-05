package com.jikan.pexelsapplication.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.databinding.FragmentCustomDialogBinding
import com.jikan.pexelsapplication.ui.extensions.loadBlurredImageWithPlaceholder

class CustomDialog(
    private val photoDomain: PhotoDomain,
    private val clickListener: () -> Unit,
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = FragmentCustomDialogBinding.inflate(layoutInflater)

        binding.btnNo.setOnClickListener {
            dismiss()
        }

        binding.btnYes.setOnClickListener {
            clickListener.invoke()
            dismiss()
        }

        binding.image.loadBlurredImageWithPlaceholder(
            photoDomain.srcDomain?.small,
            photoDomain.avgColor
        )

        return MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialog_Material3)
            .setCancelable(false)
            .setView(binding.root)
            .create().apply {
                window?.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT)
                )
            }
    }
}