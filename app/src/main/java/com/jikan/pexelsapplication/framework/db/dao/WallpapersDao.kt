package com.jikan.pexelsapplication.framework.db.dao

import androidx.room.*
import com.jikan.core.data.DbConstants
import com.jikan.pexelsapplication.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpapersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhotoEntity)

    @Query("SELECT * FROM ${DbConstants.PHOTO_TABLE_NAME}")
    fun getAllWallpapers(): Flow<List<PhotoEntity>>

    @Delete
    suspend fun deleteWallpaper(entity: PhotoEntity)

    @Query("SELECT * FROM ${DbConstants.PHOTO_TABLE_NAME} ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWallpaper(): PhotoEntity
}