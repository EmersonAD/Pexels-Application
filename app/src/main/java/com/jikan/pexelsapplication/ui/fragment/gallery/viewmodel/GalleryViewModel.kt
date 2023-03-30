package com.jikan.pexelsapplication.ui.fragment.gallery.viewmodel

import androidx.lifecycle.*
import com.jikan.core.model.PhotoDomain
import com.jikan.core.usecase.base.CoroutineDispatchers
import com.jikan.core.usecase.deletegalleryusecase.DeleteGalleryUseCase
import com.jikan.core.usecase.getgalleryusecase.GetGalleryUseCase
import com.jikan.pexelsapplication.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryUseCase: GetGalleryUseCase,
    private val deleteGalleryUseCase: DeleteGalleryUseCase,
    private val coroutineDispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val action = MutableLiveData<Action>()

    init {
        getGallery()
    }

    private fun getGallery() {
        action.value = Action.GetGallery
    }

    fun delete(photoDomain: PhotoDomain) {
        action.value = Action.DeleteFavorite(photoDomain)
    }

    val state: LiveData<UiState> = action.switchMap { action ->
        liveData(coroutineDispatchers.main()) {
            when (action) {
                is Action.GetGallery -> getPhotos()
                is Action.DeleteFavorite -> deletePhoto(action)
            }
        }
    }

    private suspend fun LiveDataScope<UiState>.getPhotos() {
        getGalleryUseCase().catch {
            emit(UiState.Error)
        }.collect {
            val uiState = if (it.isEmpty()) UiState.EmptyGallery else UiState.ShowGallery(it)
            emit(uiState)
        }
    }

    private suspend fun LiveDataScope<UiState>.deletePhoto(action: Action.DeleteFavorite) {
        deleteGalleryUseCase(
            DeleteGalleryUseCase.Params(action.photoDomain)
        ).watchStatus(
            loading = {},
            success = { getGallery() },
            error = { emit(UiState.Error) }
        )
    }

    sealed class Action {
        object GetGallery : Action()
        data class DeleteFavorite(val photoDomain: PhotoDomain) : Action()
    }

    sealed class UiState {
        data class ShowGallery(val photos: List<PhotoDomain>) : UiState()
        object EmptyGallery : UiState()
        object Error : UiState()
    }
}