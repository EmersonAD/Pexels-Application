package com.jikan.pexelsapplication.framework.di

import com.jikan.core.data.PopularRepository
import com.jikan.core.data.RemoteDataSource
import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import com.jikan.pexelsapplication.framework.remote.PopularRemoteDataSourceImpl
import com.jikan.pexelsapplication.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularRepository(repository: PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindRemoteDataSource(dataSourceImpl: PopularRemoteDataSourceImpl): RemoteDataSource<DataWrapperResponse>
}