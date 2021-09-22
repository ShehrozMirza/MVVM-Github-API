package com.app.github.ui.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.app.github.R
import com.app.github.models.GithubSearchModel
import com.app.github.ui.adapters.SearchAdapter
import com.app.github.utils.clickChildViewWithId
import com.app.github.utils.launchFragmentInHiltContainer
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@MediumTest
@ExperimentalCoroutinesApi
class SearchFragmentTest {

    private val mockNavController = mock(NavController::class.java)
    private lateinit var githubSearchModel :GithubSearchModel

    @Before
    fun setup() {
        githubSearchModel = GithubSearchModel()
    }

    @Test
    fun isFragmentDisplayed() {
        launchFragmentInHiltContainer<SearchFragment> {  }
        onView(withId(R.id.searchLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun isRecyclerViewRendered(){
        launchFragmentInHiltContainer<SearchFragment> {
            Thread.sleep(3000)
        }
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun clickFirstItemInList(){
        launchFragmentInHiltContainer<SearchFragment>{
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<SearchAdapter.PhotoViewHolder>(3, clickChildViewWithId(R.id.photoItems)))
    }

    @Test
    fun navigationOnClick(){
        val action = SearchFragmentDirections.actionPhotosFragmentToPhotoViewFragment(githubSearchModel)
        launchFragmentInHiltContainer<SearchFragment>{
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
        Thread.sleep(3000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<SearchAdapter.PhotoViewHolder>(10))
            .perform(RecyclerViewActions.actionOnItemAtPosition<SearchAdapter.PhotoViewHolder>(10, click()))
    }

    @Test
    fun matchItemCount() {
        var itemCount :Int? = 0
        launchFragmentInHiltContainer<SearchFragment> {
            Thread.sleep(2000)
            itemCount = this.recyclerView.adapter?.itemCount
        }
        onView(withId(R.id.recyclerView))
            .perform(itemCount?.let { count ->
            RecyclerViewActions.scrollToPosition<SearchAdapter.PhotoViewHolder>(count-1)
        })
    }
}