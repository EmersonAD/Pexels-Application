package com.jikan.pexelsapplication.ui.fragment.adapter.photoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.databinding.ItemPhotoBinding
import com.jikan.pexelsapplication.ui.extensions.loadBlurredImageWithPlaceholder

class PhotoViewHolder(
    itemBinding: ItemPhotoBinding,
    private val clickCallback: (photo: PhotoDomain) -> Unit,
    private val longClickCallback: (photo: PhotoDomain) -> Unit,
) : RecyclerView.ViewHolder(itemBinding.root) {
    private val image = itemBinding.image
    private val name = itemBinding.name

    fun bind(photo: PhotoDomain) {

        image.loadBlurredImageWithPlaceholder(
            imageUrl = photo.srcDomain?.original,
            placeholderColor = photo.avgColor
        )

        name.text = photo.photographer

        itemView.setOnClickListener {
            clickCallback(photo)
        }

        itemView.setOnLongClickListener {
            longClickCallback(photo)
            return@setOnLongClickListener true
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            photoCallback: (photo: PhotoDomain) -> Unit,
            longClickCallback: (photo: PhotoDomain) -> Unit
        ): PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemBinding, photoCallback, longClickCallback)
        }
    }
}