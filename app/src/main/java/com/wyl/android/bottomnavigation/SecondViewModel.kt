package com.wyl.android.bottomnavigation

import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {
    var imageScale = 1f

    fun imageScaleEnd(): Float = if (imageScale == 1f) 5f else 1f

    fun toggleScale() {
        imageScale = imageScaleEnd()
    }
}
