package com.wyl.android.screen

import android.os.Bundle
import com.wyl.android.R
import com.wyl.android.lifecycle.BaseActivity
import kotlinx.android.synthetic.main.screen_orientation_activity.*


/*
 * portrait(竖屏)和landscape(横屏)
 */
class OrientationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_orientation_activity)
        savedInstanceState?.let { textView.text = it.getString("text") }

        button.setOnClickListener { textView.text = getString(R.string.app_name) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text", textView.text.toString())
    }

}
