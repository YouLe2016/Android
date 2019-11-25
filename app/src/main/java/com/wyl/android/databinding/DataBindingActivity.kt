package com.wyl.android.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.wyl.android.R
import kotlinx.android.synthetic.main.data_binding_activity.*

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

        textView.setOnClickListener {
            Snackbar.make(it, viewModel.phone.value.toString(), Snackbar.LENGTH_SHORT).show()
        }


    }
}
