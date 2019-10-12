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

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-30 12:11
 * 内容描述：
 * 修改说明：
 */

@Dao
interface WordDao {
    @Insert
    fun addWords(vararg word: Word)

    @Delete
    fun deleteWords(vararg word: Word)

    @Update
    fun updateWords(vararg word: Word)

    @Query("select * from word order by wid desc")
    fun findAllWords(): LiveData<List<Word>>

    @Query("select * from word where english like :pattern order by wid desc")
    fun findAllWordsByWord(pattern: String): LiveData<List<Word>>
}