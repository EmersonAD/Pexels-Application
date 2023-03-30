package com.jikan.pexelsapplication.framework.di

import com.jikan.core.usecase.base.AppCoroutineDispatcher
import com.jikan.core.usecase.base.CoroutineDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {

    @Binds
    fun bindDispatcher(appCoroutineDispatchers: AppCoroutineDispatcher): CoroutineDispatchers
}