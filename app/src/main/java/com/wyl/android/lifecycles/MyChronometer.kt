package com.wyl.android.lifecycles

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.wyl.android.R
import com.wyl.android.look
import kotlinx.android.synthetic.main.lifecycle_activity.*


class MyChronometer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : Chronometer(
    context,
    attrs,
    defStyle
), LifecycleObserver {
    private var _elapsedTime = 0L

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStartEvent() {
        Log.d(look, "MyChronometer: onStart")
        if (_elapsedTime != 0L) {
            base = base + SystemClock.elapsedRealtime() - _elapsedTime
        }
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStopEvent() {
        Log.d(look, "MyChronometer: onStop")
        _elapsedTime = SystemClock.elapsedRealtime()
        stop()
    }
}
