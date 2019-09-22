package com.wyl.android.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val Number = "number"

class MySaveStateViewModel(handle: SavedStateHandle) : ViewModel() {
    val number by lazy {
        handle.getLiveData(Number, 0)

        // 太麻烦的写法
        /*if (!handle.contains(Number)) {
            handle.set(Number, 0)
        }
        handle.getLiveData<Int>(Number)*/
    }

    fun add(i: Int) {
        number.value?.let { number.value = it + i }
    }

}