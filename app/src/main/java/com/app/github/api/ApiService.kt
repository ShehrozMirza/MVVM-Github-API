package com.app.github.api


import com.app.github.globals.*
import com.app.github.models.GithubApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(GIT_SEARCH_USER_API_ROUTE)
    suspend fun searchUsers(
            @Query("q") query: String? = DEFAULT_STRING,
            @Query("sort")sort:String? = DEFAULT_STRING,
            @Query("page") page: Int? = STARTING_PAGE_INDEX,
            @Query("per_page") perPage: Int? = PER_PAGE
    ): GithubApiResponse
}