package com.app.github.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.app.github.models.GithubSearchModel
import com.app.github.util.FakeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import org.junit.Assert.assertEquals
/*

class SearchPagingSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `reviews paging source load - failure - http error`() = runBlocking {
        val repository = FakeRepository(true)
        val searchPagingSource = SearchPagingSource(repository,"A")

        val expectedResult = PagingSource.LoadResult.Error<Int, GithubSearchModel>(IOException())

        val result = searchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        assertEquals(result,expectedResult)

    }
}*/
