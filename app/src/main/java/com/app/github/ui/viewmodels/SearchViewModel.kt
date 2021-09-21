package com.app.github.ui.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.app.github.data.SearchPagingSource
import com.app.github.data.repository.DataRepository
import com.app.github.globals.DEFAULT_QUERY_STRING
import com.app.github.globals.DEFAULT_STRING
import com.app.github.globals.PER_PAGE

class SearchViewModel @ViewModelInject constructor(
    private val repository: DataRepository,
) : ViewModel() {
    @VisibleForTesting(otherwise = PRIVATE )
    internal val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        Pager(
            config = PagingConfig(
                pageSize = PER_PAGE,
                maxSize = PER_PAGE * 5,
                enablePlaceholders = false
            ), pagingSourceFactory = { SearchPagingSource(repository, queryString) }
        ).liveData.cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY: String = DEFAULT_STRING
        private const val DEFAULT_QUERY: String = DEFAULT_QUERY_STRING
    }
}