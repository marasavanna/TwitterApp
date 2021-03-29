package com.example.twitterapp.data

import com.google.gson.annotations.Expose


data class UserDataResponse(@Expose val data: List<UserData>?)

data class UserData(@Expose val id: String?, @Expose val username: String?, @Expose val profile_image_url: String?)