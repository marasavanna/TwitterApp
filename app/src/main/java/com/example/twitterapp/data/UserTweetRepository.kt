package com.example.twitterapp.data

import com.example.twitterapp.model.UserDisplay
import com.example.twitterapp.mapper.UserDisplayMapper
import com.example.twitterapp.network.UserTweetService
import io.reactivex.Maybe


class  UserTweetRepository(
    private val userTweetService: UserTweetService,
    private val userDisplayMapper: UserDisplayMapper
) {
    fun getUserDisplay(userHandle: String): Maybe<UserDisplay> {
        return userTweetService.getUserInfoByHandle(userHandle)
            .zipWith(userTweetService.getUserLastTweet(userHandle), { t1, t2 ->
                userDisplayMapper.mapToUserDisplay(t1, t2.first())
            })
    }
}
