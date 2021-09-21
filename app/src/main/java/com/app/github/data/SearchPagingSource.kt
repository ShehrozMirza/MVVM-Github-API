package com.app.github.data

import androidx.paging.PagingSource
import com.app.github.api.ApiService
import com.app.github.data.repository.DataRepository
import com.app.github.globals.PER_PAGE
import com.app.github.globals.STARTING_PAGE_INDEX
import com.app.github.models.GithubSearchModel
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(private val repository: DataRepository, private val searchQuery: String) :
    PagingSource<Int, GithubSearchModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubSearchModel> {

        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = repository.getSearchResults(
                query = searchQuery,
                sort = "alphabetically",
                page = position,
                perPage = PER_PAGE
            )
            val items = response.items

            LoadResult.Page(
                data = items,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (items.isEmpty()) null else position + 1
            )
        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}