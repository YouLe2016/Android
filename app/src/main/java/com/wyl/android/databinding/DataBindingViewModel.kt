package com.wyl.android.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-21 10:18
 * 内容描述：
 * 修改说明：
 */
class DataBindingViewModel : ViewModel() {
    val phone = MutableLiveData("")

    val number = MutableLiveData(0)

    fun add(n: Int) {
        number.value?.let {
            number.value = it + n
        }
    }
}
