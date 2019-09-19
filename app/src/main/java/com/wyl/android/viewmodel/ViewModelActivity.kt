package com.wyl.android.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R
import kotlinx.android.synthetic.main.view_model_activity.*

class ViewModelActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        supportActionBar?.title = "ViewModel"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_model_activity)

        textView.text = viewModel.number.toString()

        add.setOnClickListener { textView.text = (++viewModel.number).toString() }

        reduce.setOnClickListener { textView.text = (--viewModel.number).toString() }

    }
}
