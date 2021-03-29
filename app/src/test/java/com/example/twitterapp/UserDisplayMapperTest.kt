package com.example.twitterapp

import com.example.twitterapp.data.UserData
import com.example.twitterapp.data.UserDataResponse
import com.example.twitterapp.data.UserTweetResponse
import com.example.twitterapp.mapper.UserDisplayMapper
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserDisplayMapperTest {

    private val mapper = UserDisplayMapper()

    @Mock
    lateinit var userDataResponse: UserDataResponse

    @Mock
    lateinit var userData: UserData

    @Mock
    lateinit var userTweetResponse: UserTweetResponse

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userData = Mockito.mock(UserData::class.java)
        whenever(userData.username).thenReturn("name")
        whenever(userData.id).thenReturn("id")

        userTweetResponse = Mockito.mock(UserTweetResponse::class.java)
        whenever(userTweetResponse.id).thenReturn(10)
        whenever(userTweetResponse.text).thenReturn("tweet")

        userDataResponse = Mockito.mock(UserDataResponse::class.java)
        whenever(userDataResponse.data).thenReturn(listOf(userData, userData))
    }


    @Test
    fun userDisplayIsMappedCorrectly() {
        val userDisplay = mapper.mapToUserDisplay(userDataResponse, userTweetResponse)
        assert(userDisplay.username == userDataResponse.data?.first()?.username)
        assert(userDisplay.tweetText == userTweetResponse.text)
    }
}