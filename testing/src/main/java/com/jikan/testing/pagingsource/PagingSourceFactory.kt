package com.jikan.testing.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jikan.core.model.PhotoDomain

class PagingSourceFactory {

    fun create(photos: List<PhotoDomain>) = object : PagingSource<Int, PhotoDomain>(){

        override fun getRefreshKey(state: PagingState<Int, PhotoDomain>): Int = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDomain> =
            LoadResult.Page(data = photos, prevKey = null, nextKey = 40)
    }
}