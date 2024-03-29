package com.jikan.core.usecase.popularusecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jikan.core.data.PopularRepository
import com.jikan.core.model.PhotoDomain
import com.jikan.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularUseCase {
    operator fun invoke(params: GetPopularParams): Flow<PagingData<PhotoDomain>>
    data class GetPopularParams(val pagingConfig: PagingConfig)
}

class GetPopularUseCaseImpl @Inject constructor(
    private val repository: PopularRepository,
) : PagingUseCase<GetPopularUseCase.GetPopularParams, PhotoDomain>(), GetPopularUseCase {

    override fun createFlowObservable(params: GetPopularUseCase.GetPopularParams): Flow<PagingData<PhotoDomain>> {
        val pagingSource = repository.fetchPopular(pages = params.pagingConfig.pageSize)
        return Pager(config = params.pagingConfig) { pagingSource }.flow
    }
}