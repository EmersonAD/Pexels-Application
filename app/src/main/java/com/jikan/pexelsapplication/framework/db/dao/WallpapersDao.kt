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
    suspend fun getAllWallpapers(): Flow<List<PhotoEntity>>

    @Query("DELETE FROM ${DbConstants.PHOTO_TABLE_NAME} WHERE id = :id")
    suspend fun deleteWallpaperById(id: Int)
}