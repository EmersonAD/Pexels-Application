package com.jikan.pexelsapplication.ui.fragment.adapter.photoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.R
import com.jikan.pexelsapplication.databinding.ItemPhotoBinding

class PhotoViewHolder(
    itemBinding: ItemPhotoBinding,
    private val photoCallback: (photo: PhotoDomain) -> Unit,
) : RecyclerView.ViewHolder(itemBinding.root) {
    private val image = itemBinding.image
    private val name = itemBinding.name

    fun bind(photo: PhotoDomain) {
        Glide.with(itemView.context)
            .load(photo.srcDomain.original)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(image)

        name.text = photo.photographer

        itemView.setOnClickListener {
            photoCallback(photo)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            photoCallback: (photo: PhotoDomain) -> Unit,
        ): PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemBinding, photoCallback)
        }
    }
}