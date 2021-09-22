package com.app.github.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.github.data.repository.DataRepository
import com.app.github.util.LiveDataUtils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @Mock
    private lateinit var dataRepository: DataRepository
    private lateinit var viewModel:SearchViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val query = "some query"

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = SearchViewModel(dataRepository)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun  `search photos`() = runBlockingTest {
        viewModel.searchPhotos(query)
        assertThat(viewModel.currentQuery.value).isEqualTo(query)
    }

    @Test
    fun searchUser_canGetResponse() = runBlockingTest {
        viewModel.searchPhotos(query)
        val liveData = viewModel.photos.getOrAwaitValueTest()
        assertThat(liveData).isNotNull()

    }
}