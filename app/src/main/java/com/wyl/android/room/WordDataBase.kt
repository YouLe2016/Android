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
package com.wyl.android.room

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

@Database(entities = [Word::class], version = 4)
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
                )
//                    .fallbackToDestructiveMigration() // 不采取任何升级策略
                    .addMigrations(Migration2())
                    .addMigrations(Migration3())
                    .build()
                instance
            }
        }
    }

    abstract fun wordDao(): WordDao
}

// 添加列
class Migration2 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table word add column temp2 integer not null default 1")
    }
}

// 删除列
class Migration3 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("create table word_temp (wid integer primary key not null, english text, chinese_meaning text)")
        database.execSQL("insert into word_temp(wid, english, chinese_meaning)select wid, english, chinese_meaning from word")
//        database.execSQL("insert into word_temp(wid, english, chinese_meaning)select * from word")
        database.execSQL("drop table word")
        database.execSQL("alter table word_temp rename to word")
    }
}