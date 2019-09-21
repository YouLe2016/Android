package com.wyl.android.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R

/*
2019-9-21 10:51:04
TODO: 可以深究的点: 为什么DataBinding可以这么写
 */
class DataBindingActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(DataBindingViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<DataBindingActivityBinding>(
            this,
            R.layout.data_binding_activity
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.data = viewModel
        binding.lifecycleOwner = this
    }
}
