package com.wyl.android.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R
import com.wyl.android.databinding.ViewModelSaveStateActivityBinding

/*
2019-9-21 10:51:04
 */
class SaveStateViewModelActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(
            this,
            SavedStateViewModelFactory(application, this)
        ).get(MySaveStateViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ViewModelSaveStateActivityBinding>(
            this,
            R.layout.view_model_save_state_activity
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.data = viewModel
        binding.lifecycleOwner = this
    }
}
