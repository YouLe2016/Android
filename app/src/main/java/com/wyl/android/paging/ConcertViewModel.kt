/**
 * created by 江心才子, 2019/10/10 0010
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
package com.wyl.android.paging

import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData


/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-10-10 14:42
 * 内容描述：
 * 修改说明：
 */
class ConcertViewModel : ViewModel() {
    private val concertFactory by lazy { ConcertFactory() }
    private val dataSource by lazy { concertFactory.create() }
    val dataList by lazy {
        //        LivePagedListBuilder(concertFactory, 50).build()

        LivePagedListBuilder(
            concertFactory, PagedList.Config.Builder()
                .setPageSize(20)                         //配置分页加载的数量
                .setEnablePlaceholders(false)            //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(50)              //初始化加载的数量
                .build()
        ).build()

    }

    override fun onCleared() {
        dataSource.invalidate()
    }

}

