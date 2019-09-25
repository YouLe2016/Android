/**
 * created by 江心才子, 2019/9/25 0025
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
package com.wyl.android.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.wyl.android.viewmodel.Number
import kotlin.math.max


/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-25 12:12
 * 内容描述：
 * 修改说明：
 */

class NavigationViewModel(
    application: Application,
    handle: SavedStateHandle
) : AndroidViewModel(application) {
    val number by lazy {
        handle.getLiveData(Number, 0)
    }

    fun add(i: Int) {
        number.value?.let {
            number.value = max(0, it + i)
        }
    }
}