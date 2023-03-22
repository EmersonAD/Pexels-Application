package com.jikan.pexelsapplication.framework.local

import com.jikan.core.data.dbrepository.GalleryLocalDataSource
import com.jikan.core.model.PhotoDomain
import com.jikan.pexelsapplication.framework.db.dao.WallpapersDao
import com.jikan.pexelsapplication.framework.db.entity.PhotoEntity
import com.jikan.pexelsapplication.framework.db.entity.toPhotoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GalleryLocalDataSourceImpl @Inject constructor(
    private val dao: WallpapersDao
) : GalleryLocalDataSource {

    override suspend fun insert(domain: PhotoDomain) = dao.insert(domain.toEntity())

    override suspend fun getAll(): Flow<List<PhotoDomain>> = dao.getAllWallpapers().map {
        it.toPhotoDomain()
    }

    override suspend fun delete(domain: PhotoDomain) = dao.deleteWallpaper(domain.toEntity())

    private fun PhotoDomain.toEntity() = PhotoEntity(
        id = this.id ?: 0,
        photo = this.srcDomain?.original ?: "",
        smallPhoto = this.srcDomain?.small ?: "",
        photographer = this.photographer ?: "",
        avgColor = this.avgColor ?: ""
    )
}