package com.wyl.android.screen

import android.os.Bundle
import com.wyl.android.R
import com.wyl.android.lifecycle.BaseActivity
import kotlinx.android.synthetic.main.screen_orientation_activity.*


/*
 * portrait(竖屏)和landscape(横屏)
 */
class OrientationActivity : BaseActivity() {

    private var text = "Welcome"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_orientation_activity)
        savedInstanceState?.let { textView.text = it.getString("text") }

//        updateUi()

        button.setOnClickListener {
//            text = getString(R.string.app_name)
//            updateUi()

            textView.text = getString(R.string.app_name)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text", textView.text.toString())
    }

    /* 这种方法是不行滴, onCreate后text也初始化了 */
    private fun updateUi() {
        textView.text = text
    }

}
