package com.wyl.multiple

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getData(): String {
        textView.text = resources.getString(R.string.msg)
        return "学习机"
    }


}
