package com.wyl.multiple

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class PhoneActivity : BaseActivity() {
    override fun getData(): String {
        textView.text = resources.getString(R.string.msg)
        return "手机"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        button.setOnClickListener { startActivity(intent) }
    }

}
