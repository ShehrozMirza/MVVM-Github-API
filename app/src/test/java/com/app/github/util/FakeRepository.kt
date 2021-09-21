package com.app.github.util

import com.app.github.data.repository.DataRepository
import com.app.github.models.GithubApiResponse
import com.app.github.models.GithubSearchModel
import java.io.IOException

class FakeRepository(private val forceException : Boolean = false) : DataRepository {
    override suspend fun getSearchResults(
        query: String?,
        sort: String?,
        page: Int?,
        perPage: Int
    ): GithubApiResponse {

        if(forceException) {
            throw IOException()
        }
        return GithubApiResponse(
            676765, false, listOf(
                GithubSearchModel(id = 1410106),
                GithubSearchModel(id = 45491),
                GithubSearchModel(id = 313874),
            )
        )
    }

}
