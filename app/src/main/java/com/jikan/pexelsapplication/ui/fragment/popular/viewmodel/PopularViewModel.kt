package com.jikan.pexelsapplication.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jikan.core.model.PhotoDomain
import com.jikan.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase,
) : ViewModel() {

    fun popularWallpapers(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(GetPopularUseCase.GetPopularParams(pagingConfig)).cachedIn(viewModelScope)
    }

    private val pagingConfig = PagingConfig(pageSize = 40)
}