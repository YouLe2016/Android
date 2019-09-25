package com.wyl.android.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R
import kotlinx.android.synthetic.main.live_data_activity.*

class LiveDataActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.live_data_activity)

        viewModel.number.observe(this, Observer {
            textView.text = it.toString()
        })

        add.setOnClickListener { viewModel.add(1) }

        reduce.setOnClickListener {
            viewModel.add(-1)
        }

    }
}
