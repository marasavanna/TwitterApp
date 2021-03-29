package com.example.twitterapp.network

import com.example.twitterapp.data.UserDataResponse
import com.example.twitterapp.data.UserTweetResponse
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface UserTweetService {

    @GET("2/users/by")
    fun getUserInfoByHandle(@Query("usernames") handle: String, @Query("user.fields") imageField: String = "profile_image_url"): Maybe<UserDataResponse>

    @GET("1.1/statuses/user_timeline.json")
    fun getUserLastTweet(
        @Query("screen_name") screenName: String,
        @Query("count") count: Int = 1
    ): Maybe<List<UserTweetResponse>>
}