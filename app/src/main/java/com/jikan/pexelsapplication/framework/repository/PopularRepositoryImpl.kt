package com.jikan.pexelsapplication.framework.repository

import androidx.paging.PagingSource
import com.jikan.core.data.PopularRepository
import com.jikan.core.data.RemoteDataSource
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import com.jikan.pexelsapplication.framework.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource<DataWrapperResponse>
) : PopularRepository {

    override fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain> {
        return PopularPagingSource(remoteDataSource, pages)
    }
}