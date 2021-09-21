package com.app.github.data.repository

import com.app.github.api.ApiService
import com.app.github.models.GithubApiResponse
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val apiService: ApiService) : DataRepository {

   override suspend fun getSearchResults(query: String?,
                                         sort:String?,
                                         page: Int?,
                                         perPage: Int
   ): GithubApiResponse = apiService.searchUsers(query,sort,page,perPage)
}