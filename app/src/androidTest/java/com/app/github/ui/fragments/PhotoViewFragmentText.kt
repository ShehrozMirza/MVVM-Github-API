package com.app.github.ui.fragments

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.app.github.R
import com.app.github.models.GithubSearchModel
import com.app.github.utils.factory.DataFactoryImpl
import com.app.github.utils.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test


@MediumTest
@ExperimentalCoroutinesApi
class PhotoViewFragmentText {

    private lateinit var githubSearchModel : GithubSearchModel
    private lateinit var fragmentArgsBundle:Bundle

    @Before
    fun setup() {
        githubSearchModel = DataFactoryImpl.getGithubSearchModel()
        fragmentArgsBundle = bundleOf("photo" to githubSearchModel)
    }

    @Test
    fun isFragmentDisplayed() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle) {  }
        onView(withId(R.id.photoScrollView))
            .check(matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun assertID() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_id)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().id.toString())))
    }


    @Test
    fun assertNodeID() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_node_id)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().nodeId.toString())))
    }


    @Test
    fun assertAvatarURL() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_avatar_url)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().avatarUrl.toString())))
    }


    @Test
    fun assertURL() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_url)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().url.toString())))
    }

    @Test
    fun assertHTMLID() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_html_url)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().htmlUrl.toString())))
    }


    @Test
    fun assertSubscriptionURL() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_subscriptions_url)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().subscriptionsUrl.toString())))
    }


    @Test
    fun assertFollowerURL() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_follower_url)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().followersUrl.toString())))
    }

    @Test
    fun assertFollowingURL() {
        launchFragmentInHiltContainer<PhotoViewFragment>(fragmentArgs = fragmentArgsBundle)
        onView(withId(R.id.txt_following_url)).check(matches(withText(DataFactoryImpl.getGithubSearchModel().followingUrl.toString())))
    }
}