package com.example.twitterapp.ui

import com.example.twitterapp.bases.BaseActivity
import com.example.twitterapp.R
import com.example.twitterapp.databinding.ActivityUserDisplayBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDisplayActivity : BaseActivity<ActivityUserDisplayBinding, UserDisplayViewModel>() {
    override val layoutRes: Int
        get() = R.layout.activity_user_display
    override val viewModel: UserDisplayViewModel by viewModel()

    override fun initViewState() {
        super.initViewState()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val handle = intent.extras?.getString(twitterHandleKey)
        handle?.let { viewModel.getData(it) }

        binding.close.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val twitterHandleKey = "handle"
    }
}