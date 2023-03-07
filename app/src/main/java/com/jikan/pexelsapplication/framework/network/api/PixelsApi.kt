package com.jikan.pexelsapplication.framework.network.api

import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixelsApi {

    @GET("v1/curated")
    suspend fun getPopularWallpapers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): DataWrapperResponse
}