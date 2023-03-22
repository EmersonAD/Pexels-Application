package com.jikan.pexelsapplication.framework.di

import com.jikan.core.data.dbrepository.GalleryLocalDataSource
import com.jikan.core.data.dbrepository.GalleryRepository
import com.jikan.pexelsapplication.framework.db.repository.GalleryRepositoryImpl
import com.jikan.pexelsapplication.framework.local.GalleryLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GalleryRepositoryModule {

    @Binds
    fun bindGalleryRepository(galleryRepositoryImpl: GalleryRepositoryImpl): GalleryRepository

    @Binds
    fun bindLocalDataSource(localDataSourceImpl: GalleryLocalDataSourceImpl): GalleryLocalDataSource
}