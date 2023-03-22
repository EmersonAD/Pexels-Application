package com.jikan.core.data.dbrepository

import com.jikan.core.model.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface GalleryLocalDataSource {
    suspend fun insert(domain: PhotoDomain)
    suspend fun getAll(): Flow<List<PhotoDomain>>
    suspend fun delete(domain: PhotoDomain)
}