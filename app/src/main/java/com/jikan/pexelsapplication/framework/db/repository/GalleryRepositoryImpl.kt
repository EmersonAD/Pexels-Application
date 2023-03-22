package com.jikan.pexelsapplication.framework.db.repository

import com.jikan.core.data.dbrepository.GalleryLocalDataSource
import com.jikan.core.data.dbrepository.GalleryRepository
import com.jikan.core.model.PhotoDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val dataSource: GalleryLocalDataSource
) : GalleryRepository {

    override suspend fun insert(domain: PhotoDomain) = dataSource.insert(domain)

    override suspend fun getAll(): Flow<List<PhotoDomain>> = dataSource.getAll()

    override suspend fun delete(domain: PhotoDomain) = dataSource.delete(domain)
}