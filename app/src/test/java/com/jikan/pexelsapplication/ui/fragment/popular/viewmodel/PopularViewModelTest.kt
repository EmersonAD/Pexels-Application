package com.jikan.pexelsapplication.ui.fragment.popular.viewmodel

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.jikan.core.model.PhotoDomain
import com.jikan.core.model.SrcDomain
import com.jikan.core.usecase.popularusecase.GetPopularUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
internal class PopularViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    @Mock
    lateinit var popularUseCase: GetPopularUseCase
    private lateinit var popularViewModel: PopularViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        popularViewModel = PopularViewModel(popularUseCase)
    }

    @Test
    fun `Should validate pagination data`() = runTest {
        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock()))

        val result = popularViewModel.popularWallpapers()

        Assert.assertNotNull(result.first())
    }

    @Test
    fun `Should return an empty PagingData When an error occurred`() = runTest {
        //Arrange
        val exception = Exception()
        val expected = PagingData.empty<PhotoDomain>()
        whenever(popularUseCase(any())).thenAnswer { flow<PagingData<PhotoDomain>> { throw exception } }

        //Act
        val result = popularViewModel.popularWallpapers().first()

        //Assert
        Assert.assertEquals(result, expected)
    }


    private fun getPagingDataMock() =
        PagingData.from(listOf(domain, domain, domain, domain, domain, domain))

    private val domain = PhotoDomain(
        description = "Free stock photo of agriculture, architecture, bright",
        avgColor = "#56543B",
        height = 6240,
        id = 15575287,
        liked = false,
        photographer = "medinegurbet",
        photographerId = 21435626,
        photographerUrl = "https://www.pexels.com/@medinegurbet-21435626",
        srcDomain = SrcDomain(
            landscape = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            large = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
            large2x = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            medium = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&h=350",
            original = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg",
            portrait = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
            small = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&h=130",
            tiny = "https://images.pexels.com/photos/15575287/pexels-photo-15575287.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
        ),
        url = "https://www.pexels.com/photo/wood-summer-garden-house-15575287/",
        width = 4160
    )
}