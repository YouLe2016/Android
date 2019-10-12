/**
 * created by 江心才子, 2019/9/30 0030
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
package com.wyl.word

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-30 14:01
 * 内容描述：
 * 修改说明：
 */

@Database(entities = [Word::class], version = 1)
abstract class WordDataBase : RoomDatabase() {
    companion object {
        private lateinit var instance: WordDataBase

        @Synchronized
        fun getInstance(context: Context): WordDataBase {
            return if (this::instance.isInitialized) {
                instance
            } else {
                instance = Room.databaseBuilder(
                    context,
                    WordDataBase::class.java,
                    "word_database"
                ).build()
                instance
            }
        }

    }

    abstract fun wordDao(): WordDao
}
