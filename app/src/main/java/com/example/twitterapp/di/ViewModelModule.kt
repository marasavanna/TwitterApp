package com.example.twitterapp.di

import com.example.twitterapp.data.UserTweetRepository
import com.example.twitterapp.mapper.UserDisplayMapper
import com.example.twitterapp.network.UserTweetService
import com.example.twitterapp.ui.InputHandleViewModel
import com.example.twitterapp.ui.UserDisplayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        provideUserTweetRepository(get(), get())
    }
    single {
        provideUserDisplayMapper()
    }
    viewModel { InputHandleViewModel() }
    viewModel { UserDisplayViewModel(get()) }

}

fun provideUserTweetRepository(
    userTweetService: UserTweetService,
    userDisplayMapper: UserDisplayMapper
): UserTweetRepository = UserTweetRepository(userTweetService, userDisplayMapper)

fun provideUserDisplayMapper(): UserDisplayMapper = UserDisplayMapper()