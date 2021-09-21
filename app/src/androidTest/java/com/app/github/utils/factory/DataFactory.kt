package com.app.github.utils.factory

import com.app.github.models.GithubSearchModel

interface DataFactory {
    fun getGithubSearchModel():GithubSearchModel
}