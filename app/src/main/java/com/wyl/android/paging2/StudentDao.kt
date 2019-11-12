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

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-11-12 11:44
 * 内容描述：
 * 修改说明：
 */
@Dao
interface StudentDao {
    @Insert
    fun insertStudent(vararg student: Student)

    @Query("delete from Student")
    fun deleteAllStudent()

    @Query("select * from Student order by student_id")

//    fun findAllStudent(): LiveData<List<Student>>
    fun findAllStudent(): DataSource.Factory<Int, Student>

}