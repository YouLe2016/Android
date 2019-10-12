package com.wyl.word.extend

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.wyl.word.KLog


/**
 * 自动获取属性值
 */
fun Fragment.openActivity(activity: Class<*>, block: (Intent.() -> Intent)? = null) {
    var intent = Intent(context, activity)
    block?.let { intent = block.invoke(intent) }
    startActivity(intent)
}

/**
 * 自动获取属性值
 */
fun <T> Fragment.autoWired(key: String, default: T): T {
    KLog.d("key = $key")
    return arguments!!.let {
        KLog.d("value = ${it.get(key)}")
        if (it.get(key) != null) {
            try {
                it.get(key) as T
            } catch (e: ClassCastException) {
                e.printStackTrace()
                default
            }
        } else default
    }
}

/**
 * 开启事务处理fragment
 */
fun Fragment.transact(block: FragmentTransaction.() -> FragmentTransaction) {
    childFragmentManager.beginTransaction().block().commit()
}

// toast
fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(text, duration)
}

fun Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(resId, duration)
}
