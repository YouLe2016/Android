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

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


/**
 * 项目名称：android-learn
 * 创建人：江心才子
 * 创建时间：2019-09-30 11:54
 * 内容描述：
 * 修改说明：
 */
@Entity
class Word(
    @ColumnInfo(name = "english")
    var word: String,
    @ColumnInfo(name = "chinese_meaning")
    var chineseMeaning: String,
    @ColumnInfo(name = "hide_meaning")
    var hideMeaning: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var wid: Int = 0

    override fun toString(): String {
        return "Word(word='$word', chineseMeaning='$chineseMeaning', wid=$wid)"
    }


}