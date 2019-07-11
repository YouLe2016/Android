/**
 * created by 乐哥哥, 2019/7/11
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
package com.wyl.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * 项目名称：android-learn
 * 创建人：乐哥哥
 * 创建时间：2019-07-11 16:38
 */
class NormalFragmentDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(activity!!)
            .setTitle("普通的DIALOG")
            .setMessage("这是一个普通的dialog")
            .setNegativeButton("确定") { dialog, _ -> dialog.dismiss() }
            .setPositiveButton("取消") { dialog, _ -> dialog.dismiss() }
            .setCancelable(true)
            .create()
        alertDialog.setCanceledOnTouchOutside(true)
        //和普通的dialog没什么区别了，想干什么就干什么了
        return alertDialog
    }

}