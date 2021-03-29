package com.example.twitterapp.mapper

import com.example.twitterapp.data.UserDataResponse
import com.example.twitterapp.data.UserTweetResponse
import com.example.twitterapp.model.UserDisplay

class UserDisplayMapper {

    fun mapToUserDisplay(userDataResponse: UserDataResponse, userTweetResponse: UserTweetResponse) =
        UserDisplay(
            userDataResponse.data?.first()?.username,
            userDataResponse.data?.first()?.profile_image_url,
            userTweetResponse.text
        )
}