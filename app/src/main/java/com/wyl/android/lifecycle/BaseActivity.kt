/**
 * created by 江心才子, 2019/9/19 0019
 * Copyright (c) 2019, 270628297@qq.com All Rights Reserved.
 * #                   *********                            #
 * #                  ************                          #
 * #                  *************                         #
 * #                 **  ***********                        #
 * #                ***  ****** *****                       #
 * #                *** *******   ****                      #
 * #               ***  ********** ****                     #
 * #              ****  *********** ****                    #
 * #            *****   ***********  *****                  #
 * #           ******   *** ********   *****                #
 * #           *****   ***   ********   ******              #
 * #          ******   ***  ***********   ******            #
 * #         ******   **** **************  ******           #
 * #        *******  ********************* *******          #
 * #        *******  ******************************         #
 * #       *******  ****** ***************** *******        #
 * #       *******  ****** ****** *********   ******        #
 * #       *******    **  ******   ******     ******        #
 * #       *******        ******    *****     *****         #
 * #        ******        *****     *****     ****          #
 * #         *****        ****      *****     ***           #
 * #          *****       ***        ***      *             #
 * #            **       ****        ****                   #
 */
package com.wyl.android.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-19 13:36
 * 内容描述：
 * 修改说明：
 */

const val Lifecycle = "lifecycle"

open class BaseActivity : AppCompatActivity() {
    protected val tag = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(Lifecycle, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onRestart() {
        Log.d(Lifecycle, "onRestart")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(Lifecycle, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(Lifecycle, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(Lifecycle, "onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(Lifecycle, "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Log.d(Lifecycle, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(Lifecycle, "onDestroy")
        super.onDestroy()
    }

}