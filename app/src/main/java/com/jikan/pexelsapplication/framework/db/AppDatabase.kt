package com.jikan.pexelsapplication.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jikan.pexelsapplication.framework.db.dao.WallpapersDao
import com.jikan.pexelsapplication.framework.db.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wallpapersDao(): WallpapersDao
}