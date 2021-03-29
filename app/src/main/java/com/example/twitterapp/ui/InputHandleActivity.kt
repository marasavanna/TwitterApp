package com.example.twitterapp.ui

import com.example.twitterapp.R
import com.example.twitterapp.bases.BaseActivity
import com.example.twitterapp.databinding.ActivityHandleInputBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class InputHandleActivity : BaseActivity<ActivityHandleInputBinding, InputHandleViewModel>() {

    override val layoutRes: Int
        get() = R.layout.activity_handle_input

    override val viewModel: InputHandleViewModel by viewModel()

    override fun initViewState() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}