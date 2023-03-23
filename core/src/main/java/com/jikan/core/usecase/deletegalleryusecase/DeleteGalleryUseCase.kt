package com.jikan.core.usecase.deletegalleryusecase

import com.jikan.core.data.dbrepository.GalleryRepository
import com.jikan.core.model.PhotoDomain
import com.jikan.core.usecase.base.CoroutineDispatchers
import com.jikan.core.usecase.base.ResultStatus
import com.jikan.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DeleteGalleryUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
    data class Params(val photoDomain: PhotoDomain)
}

class DeleteGalleryUseCaseImpl @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dispatchers: CoroutineDispatchers,
) : UseCase<DeleteGalleryUseCase.Params, Unit>(), DeleteGalleryUseCase {

    override suspend fun doWork(params: DeleteGalleryUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            galleryRepository.delete(params.photoDomain)
            ResultStatus.Success (Unit)
        }
    }
}