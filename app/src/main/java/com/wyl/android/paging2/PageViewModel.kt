/**
 * created by 江心才子, 2019/11/12 0012
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
package com.wyl.android.paging2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import kotlin.concurrent.thread

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-11-12 12:28
 * 内容描述：
 * 修改说明：
 */
class PageViewModel(app: Application) : AndroidViewModel(app) {
    private val studentDao by lazy {
        StudentDataBase.getInstance(app).getStudentDao()
    }

    //    LiveData<PagedList<Student>>()
    var dataSource = LivePagedListBuilder<Int, Student>(
        studentDao.findAllStudent(),
        20
    ).build()

    fun insertData() {
        thread {
            studentDao.insertStudent(
                * Array(50) { Student(it + 1) }
            )
        }
    }

    fun deleteAll() {
        thread { studentDao.deleteAllStudent() }
    }

    override fun onCleared() {
        StudentDataBase.getInstance(getApplication()).close()
    }
}