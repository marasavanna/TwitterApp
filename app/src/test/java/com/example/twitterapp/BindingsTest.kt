package com.example.twitterapp

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BindingsTest {

    @Mock
    lateinit var editText: EditText

    @Mock
    lateinit var textView: TextView

    @Mock
    lateinit var context: Context

    @Captor
    lateinit var stringCaptor: ArgumentCaptor<String>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        whenever(editText.context).thenReturn(context)
        whenever(textView.context).thenReturn(context)
    }

    @Test
    fun editTextHasNoErrorWhenHandleNotEmpty() {
        val handle = "handle"
        editText.handleErrorState(handle)
        verify(editText).error = capture(stringCaptor)
        assert(stringCaptor.value.isNullOrEmpty())
    }

    @Test
    fun editTextHasErrorWhenHandleIsEmpty() {
        val handle = ""
        editText.handleErrorState(handle)
        verify(editText).error = capture(stringCaptor)
        assert(stringCaptor.value.isNotEmpty())
    }

    @Test
    fun tweetHasPlaceholderMessageWhenEmpty() {
        textView.setTweetTextDependingOnUserStatus("")
        verify(textView).text = capture(stringCaptor)
        assert(stringCaptor.value == context.getString(R.string.empty_tweet_placeholder_message))
    }

    @Test
    fun tweetHasActualMessageWhenNotEmpty() {
        val tweetText = "tweet"
        textView.setTweetTextDependingOnUserStatus(tweetText)
        verify(textView).text = capture(stringCaptor)
        assert(stringCaptor.value == tweetText)
    }
}