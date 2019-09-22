package com.wyl.android.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class MyAndroidViewModel(application: Application) : AndroidViewModel(application) {
    var number = 0

    fun toast() {
        val context = getApplication<Application>()
        Toast.makeText(context, "from AndroidViewModel", Toast.LENGTH_LONG).show()
    }


}