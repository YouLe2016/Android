package com.wyl.android.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R
import kotlinx.android.synthetic.main.view_model_android_activity.*

/*
AndroidViewModel
 */
class AndroidViewModelActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MyAndroidViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_model_android_activity)

        textView.text = viewModel.number.toString()
        textView.setOnClickListener { viewModel.toast() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
