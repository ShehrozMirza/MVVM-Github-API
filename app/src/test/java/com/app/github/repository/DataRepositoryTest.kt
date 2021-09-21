package com.app.github.repository

import com.app.github.api.ApiService
import com.app.github.data.repository.DataRepository
import com.app.github.data.repository.DataRepositoryImpl
import com.app.github.models.GithubApiResponse
import com.app.github.models.GithubSearchModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DataRepositoryTest {

    private lateinit var mockApiService: ApiService
    private lateinit var repository: DataRepository

    private val networkData = GithubApiResponse(676765,false, listOf(
        GithubSearchModel(id = 1410106),
        GithubSearchModel(id = 45491),
        GithubSearchModel(id = 313874),
    ))

    @Before
    fun createRepository() = runBlocking {
        mockApiService = mock(ApiService::class.java)

        `when`(mockApiService.searchUsers("A","alphabetically",1,3)).thenReturn(networkData)

        repository = DataRepositoryImpl(mockApiService)
    }

    @Test
    fun searchUser_returnsExactly3Items() = runBlocking {
        val data = repository.getSearchResults("A","alphabetically",1,3)
        assertTrue(data.items.size == 3)
    }

    @Test
    fun searchUser_returnsTotalCount676765() = runBlocking {
        val data = repository.getSearchResults("A","alphabetically",1,3)
        assertEquals(data.total_count,676765)
    }
}