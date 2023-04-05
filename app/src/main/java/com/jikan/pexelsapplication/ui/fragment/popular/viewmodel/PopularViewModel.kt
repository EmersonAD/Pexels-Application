package com.jikan.pexelsapplication.ui.fragment.popular.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jikan.core.model.PhotoDomain
import com.jikan.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.jikan.core.usecase.popularusecase.GetPopularUseCase
import com.jikan.pexelsapplication.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase,
    private val insertGalleryUseCase: InsertGalleryUseCase,
) : ViewModel() {

    private val _favoriteUiState = MutableLiveData<FavoriteUiState>()
    val favoriteUiState: LiveData<FavoriteUiState> = _favoriteUiState

    fun popularWallpapers(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(GetPopularUseCase.GetPopularParams(pagingConfig))
            .catch { emit(PagingData.empty()) }
            .cachedIn(viewModelScope)
    }

    private val pagingConfig = PagingConfig(pageSize = 40)

    fun insertData(photoDomain: PhotoDomain) {
        viewModelScope.launch {
            photoDomain.run {
                insertGalleryUseCase(
                    InsertGalleryUseCase.Params(this)
                ).watchStatus(
                    loading = {
                        _favoriteUiState.value = FavoriteUiState.Loading
                    },
                    success = {
                        _favoriteUiState.value = FavoriteUiState.FavoritePhoto(true)
                    },
                    error = {
                        _favoriteUiState.value = FavoriteUiState.FavoritePhoto(false)
                    }
                )
            }
        }
    }

    sealed class FavoriteUiState {
        object Loading : FavoriteUiState()
        class FavoritePhoto(val saved: Boolean) : FavoriteUiState()
    }
}