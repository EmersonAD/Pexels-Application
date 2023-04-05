package com.jikan.core.usecase.insertgalleryusecase

import com.jikan.core.data.dbrepository.GalleryRepository
import com.jikan.core.model.PhotoDomain
import com.jikan.core.usecase.base.CoroutineDispatchers
import com.jikan.core.usecase.base.ResultStatus
import com.jikan.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface InsertGalleryUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
    data class Params(val photoDomain: PhotoDomain)
}

class InsertGalleryUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository,
    private val dispatchers: CoroutineDispatchers,
) : UseCase<InsertGalleryUseCase.Params, Unit>(), InsertGalleryUseCase {

    override suspend fun doWork(params: InsertGalleryUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            repository.insert(params.photoDomain)
            ResultStatus.Success(Unit)
        }
    }
}