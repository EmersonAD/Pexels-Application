package com.jikan.pexelsapplication.framework.di

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
}