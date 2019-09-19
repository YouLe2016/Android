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
package com.wyl.android.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-19 14:44
 * 内容描述：
 * 修改说明：
 */
class LiveDataViewModel : ViewModel() {
    var number = MutableLiveData(0)
        private set

    fun add(n: Int) {
        number.value?.let {
            number.value = it + n
        }
    }

    fun reduce(n: Int) {
        number.value?.let {
            number.value = it - n
        }
    }

}