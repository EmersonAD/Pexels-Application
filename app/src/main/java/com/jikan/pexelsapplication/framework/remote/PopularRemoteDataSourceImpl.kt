package com.jikan.pexelsapplication.framework.remote

import com.jikan.core.data.RemoteDataSource
import com.jikan.pexelsapplication.framework.network.api.PixelsApi
import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class PopularRemoteDataSourceImpl @Inject constructor(
    private val api: PixelsApi,
) : RemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchPopular(page: Int, perPage: Int): DataWrapperResponse =
        api.getPopularWallpapers(page, perPage)
}