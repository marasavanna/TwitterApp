package com.example.twitterapp.bases

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<DB : ViewDataBinding, VM: ViewModel> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutRes: Int
    abstract val viewModel: VM

    open lateinit var binding: DB

    open fun initViewState() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, layoutRes, null, false)
        setContentView(binding.root)
        initViewState()
    }
}