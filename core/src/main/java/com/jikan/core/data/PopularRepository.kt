package com.jikan.core.data

import androidx.paging.PagingSource
import com.jikan.core.model.PhotoDomain

interface PopularRepository {
    fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain>
}