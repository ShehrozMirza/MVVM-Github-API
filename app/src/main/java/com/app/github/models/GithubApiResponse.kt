package com.app.github.models

data class GithubApiResponse(val total_count : Int, val incomplete_results : Boolean, val items : List<GithubSearchModel>)