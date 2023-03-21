package com.jikan.pexelsapplication.framework.repository

import com.google.common.truth.Truth
import com.jikan.core.data.RemoteDataSource
import com.jikan.pexelsapplication.framework.network.response.DataWrapperResponse
import com.jikan.testing.MainCoroutinesRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularRepositoryImplTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    private val remoteDataSource = mock<RemoteDataSource<DataWrapperResponse>>()
    private val subject = PopularRepositoryImpl(remoteDataSource)

    @Test
    fun `should return correctly`() = runTest {
        //Act
        val result = subject.fetchPopular(40)

        //Assert
        Truth.assertThat(result).isNotNull()
    }
}