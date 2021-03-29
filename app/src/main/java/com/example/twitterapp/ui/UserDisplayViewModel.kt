package com.example.twitterapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitterapp.data.UserTweetRepository
import com.example.twitterapp.model.UserDisplay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserDisplayViewModel(private val repository: UserTweetRepository) : ViewModel() {

    var userDisplay = MutableLiveData<UserDisplay>()
    var error = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    fun getData(userHandle: String) {
        compositeDisposable.add(
            repository.getUserDisplay(userHandle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    userDisplay.postValue(it)
                }, {
                    error.postValue(it.message)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}