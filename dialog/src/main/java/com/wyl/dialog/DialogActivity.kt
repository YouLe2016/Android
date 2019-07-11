package com.wyl.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wyl.easydialog.EasyDialogUtil

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_activity)
    }

    fun showNormalDialog(view: View) {
        NormalFragmentDialog().show(supportFragmentManager, "normal")
    }

    fun showEasyDialog(view: View) {
        val dialog = EasyDialogUtil.showLoadingDialog(supportFragmentManager)

        /*EasyDialogUtil.showConfirmDialog(
            "Easy Dialog",
            supportFragmentManager,
            object : EasyDialogUtil.ConfirmDialogCallback {
                override fun cancel(view: View) {
                    Toast.makeText(this@DialogActivity, getString(R.string.common_cancel), Toast.LENGTH_LONG).show()
                }

                override fun confirm(view: View) {
                    Toast.makeText(this@DialogActivity, getString(R.string.common_confirm), Toast.LENGTH_LONG).show()
                }
            })*/
    }
}
