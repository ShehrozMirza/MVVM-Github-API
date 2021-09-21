package com.app.github.data.repository

import com.app.github.models.GithubApiResponse

interface DataRepository {

    suspend fun getSearchResults(query: String?,
                                 sort:String?,
                                 page: Int?,
                                 perPage: Int
    ): GithubApiResponse
}