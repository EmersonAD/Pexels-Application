package com.jikan.pexelsapplication.framework.di

import android.content.Context
import androidx.room.Room
import com.jikan.core.data.DbConstants
import com.jikan.pexelsapplication.framework.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DbConstants.DB_NAME
        ).build()

    @Provides
    fun provideWallpapersDao(db: AppDatabase) = db.wallpapersDao()
}