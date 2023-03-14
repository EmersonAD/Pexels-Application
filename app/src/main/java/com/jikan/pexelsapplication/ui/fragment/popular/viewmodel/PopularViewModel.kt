package com.jikan.pexelsapplication.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import com.jikan.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase
) : ViewModel() {


}