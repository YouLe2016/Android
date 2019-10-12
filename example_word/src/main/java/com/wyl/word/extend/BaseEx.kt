package com.wyl.word.extend

import com.wyl.word.BuildConfig
import com.wyl.word.KLog


// Activity.componentName.shortClassName == javaClass.simpleName
fun Any.logD(msg: Any?, tag: String? = null) {
    if (BuildConfig.DEBUG) {
//        Log.d(tag ?: javaClass.simpleName, msg.toString())
        KLog.d(tag ?: javaClass.simpleName, msg.toString())
    }
}

fun Any.logE(msg: Any?, tag: String? = null) {
    if (BuildConfig.DEBUG) {
//        Log.e(tag ?: javaClass.simpleName, msg.toString())
        KLog.e(tag ?: javaClass.simpleName, msg.toString())
    }
    // Activity.componentName.shortClassName == javaClass.simpleName
}

