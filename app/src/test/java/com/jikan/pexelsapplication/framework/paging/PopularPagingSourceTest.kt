package com.jikan.pexelsapplication.framework.paging

import androidx.paging.PagingSource
import com.google.common.truth.Truth
import com.jikan.core.data.RemoteDataSource
import com.jikan.pexelsapplication.factory.DataWrapperResponseFactory
import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import com.jikan.pexelsapplication.framework.network.response.toPhotoDomain
import com.jikan.testing.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularPagingSourceTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()
    private val remoteDataSource = mock<RemoteDataSource<DataWrapperResponse>>()
    private val pages = 40
    private val subject = PopularPagingSource(remoteDataSource, pages)
    private val response = DataWrapperResponseFactory().create()

    @Test
    fun `should return LoadResultPage When response is correctly`() = runTest {
        //Arrange
        val parameter = PagingSource.LoadParams.Prepend(1, 40, true)

        whenever(remoteDataSource.fetchPopular(1, 40)).thenReturn(response)

        val expected = PagingSource.LoadResult.Page(
            data = response.photos.map { it.toPhotoDomain() },
            prevKey = null,
            nextKey = 2
        )

        //Act
        val result = subject.load(parameter)

        //Assert
        Truth.assertThat(result).apply {
            isNotNull()
            isEqualTo(expected)
        }
    }

    @Test(expected = Exception::class)
    fun `Should return a loadResult of error When response is not correctly`() = runTest {
        //Arrange
        val parameter = PagingSource.LoadParams.Prepend(1, 40, true)

        whenever(remoteDataSource.fetchPopular(1, 40)).thenThrow(Exception())

        //Act
        val result = subject.load(parameter)

        //Assert
        Truth.assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }

}