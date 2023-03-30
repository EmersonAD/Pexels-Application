package com.jikan.pexelsapplication.framework.di

import com.jikan.core.usecase.deletegalleryusecase.DeleteGalleryUseCase
import com.jikan.core.usecase.deletegalleryusecase.DeleteGalleryUseCaseImpl
import com.jikan.core.usecase.getgalleryusecase.GetGalleryUseCase
import com.jikan.core.usecase.getgalleryusecase.GetGalleryUseCaseImpl
import com.jikan.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.jikan.core.usecase.insertgalleryusecase.InsertGalleryUseCaseImpl
import com.jikan.core.usecase.popularusecase.GetPopularUseCase
import com.jikan.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(getPopularUseCase: GetPopularUseCaseImpl): GetPopularUseCase

    @Binds
    fun bindGetGalleryUseCase(getGalleryUseCaseImpl: GetGalleryUseCaseImpl): GetGalleryUseCase

    @Binds
    fun bindInsertGalleryUseCase(insertGalleryUseCaseImpl: InsertGalleryUseCaseImpl): InsertGalleryUseCase

    @Binds
    fun bindDeleteGalleryUseCase(deleteGalleryUseCaseImpl: DeleteGalleryUseCaseImpl): DeleteGalleryUseCase
}