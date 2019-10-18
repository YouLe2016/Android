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

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-10-10 12:17
 * 内容描述：
 * 修改说明：
 */
class ConcertDataSource : PositionalDataSource<Concert>() {
    private val dataSource = List(1_000) { Concert(it) }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Concert>) {
        callback.onResult(fetchItems(0, params.pageSize), 0, dataSource.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Concert>) {
        callback.onResult(fetchItems(params.startPosition, params.loadSize))
    }

    private fun fetchItems(startPosition: Int, loadSize: Int): List<Concert> =
        dataSource.subList(startPosition, startPosition + loadSize)

}

class ConcertFactory : DataSource.Factory<Int, Concert>() {
//    private val liveData = MutableLiveData<ConcertDataSource>()

    override fun create(): DataSource<Int, Concert> {
        val dataSource = ConcertDataSource()
//        liveData.postValue(dataSource)
        return dataSource
    }
}