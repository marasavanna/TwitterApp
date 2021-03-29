package com.example.twitterapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.twitterapp.data.UserData
import com.example.twitterapp.data.UserDataResponse
import com.example.twitterapp.data.UserTweetRepository
import com.example.twitterapp.data.UserTweetResponse
import com.example.twitterapp.mapper.UserDisplayMapper
import com.example.twitterapp.model.UserDisplay
import com.example.twitterapp.network.UserTweetService
import com.example.twitterapp.ui.UserDisplayViewModel
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Maybe
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserDisplayViewModelTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    private lateinit var userTweetRepository: UserTweetRepository

    @Mock
    private lateinit var userTweetService: UserTweetService

    private val userTweetMapper = UserDisplayMapper()

    @Mock
    lateinit var observer: Observer<UserDisplay>

    @Mock
    lateinit var viewModel: UserDisplayViewModel

    @Mock
    lateinit var userData: UserData

    @Mock
    lateinit var userTweetResponse: UserTweetResponse

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val handle = "handle"


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        whenever(userTweetService.getUserInfoByHandle(handle)).thenReturn(Maybe.just(UserDataResponse(listOf(userData))))
        whenever(userTweetService.getUserLastTweet(handle)).thenReturn(Maybe.just(listOf(userTweetResponse)))
        userTweetRepository = UserTweetRepository(userTweetService, userTweetMapper)
        viewModel = UserDisplayViewModel(userTweetRepository)
        viewModel.userDisplay.observeForever(observer)
    }

    @Test
    fun userDetailIsNotEmptyAfterFetch() {
        assert(viewModel.userDisplay.value == null)
        viewModel.getData(handle)
        assert(viewModel.userDisplay.value != null)
    }
}