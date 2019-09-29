package com.wyl.android.lifecycles

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.wyl.android.R
import com.wyl.android.lifecycle.BaseActivity
import com.wyl.android.look
import kotlinx.android.synthetic.main.lifecycle_activity.*

class LifecycleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lifecycle_activity)
        lifecycle.addObserver(chronometer)
    }

    override fun onStart() {
        super.onStart()
        Log.d(look, "LifecycleActivity: onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(look, "LifecycleActivity: onStop")
    }
}
