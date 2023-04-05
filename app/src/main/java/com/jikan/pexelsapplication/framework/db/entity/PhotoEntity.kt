package com.jikan.pexelsapplication.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jikan.core.data.DbConstants
import com.jikan.core.model.PhotoDomain
import com.jikan.core.model.SrcDomain

@Entity(tableName = DbConstants.PHOTO_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    val photo: String,
    val smallPhoto: String,
    val photographer: String,
    val avgColor: String
)

fun List<PhotoEntity>.toPhotoDomain() = map {
    PhotoDomain(
        id = it.id,
        photographer = it.photographer,
        avgColor = it.avgColor,
        srcDomain = SrcDomain(
            original = it.photo,
            small = it.smallPhoto
        )
    )
}