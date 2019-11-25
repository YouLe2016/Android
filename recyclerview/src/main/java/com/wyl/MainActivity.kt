package com.wyl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.wyl.recyclerview.R

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-11-25 11:05
 * 内容描述：
 * 修改说明：
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment).navigateUp() || super.onSupportNavigateUp()
    }
}