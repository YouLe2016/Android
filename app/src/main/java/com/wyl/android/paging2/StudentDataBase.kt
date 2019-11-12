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

import android.content.Context
import android.content.res.Resources
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wyl.android.R

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-11-12 11:54
 * 内容描述：
 * 修改说明：
 */
@Database(entities = [Student::class], version = 1)
abstract class StudentDataBase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {
        private const val TableName = "student_table"

        private lateinit var instance: StudentDataBase

        fun getInstance(context: Context): StudentDataBase {
            if (!this::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context,
                    StudentDataBase::class.java,
                    TableName
                ).build()
            }
            return instance
        }
    }
}
