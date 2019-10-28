package com.wyl.android.constraintlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.wyl.android.R
import kotlinx.android.synthetic.main.activity_constraint_layout.*


/**
 * Android:随笔—— ConstraintLayout 动画
 * url: https://www.jianshu.com/p/c585180af02b
 */
class ConstraintLayoutActivity : AppCompatActivity() {

    private val mConstraintSet1 = ConstraintSet()
    private val mConstraintSet2 = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)

        // 1.有几种动画状态就需要创建几个 ConstraintSet 因为约束布局的动画都依赖于 ConstraintSet

        // 2.把默认 constraintLayout 布局放到 mConstraintSet1 中
        // 使用这个方法把布局位置以及约束关系记录到 ConstraintSet 中
        mConstraintSet1.clone(root_layout)

        // 3.把标定位置变换的 constraintLayout 布局放到 mConstraintSet2 中
        // 我们上面说的如果不想再画一个布局那就是用代码自己编辑约束关系
        mConstraintSet2.clone(this, R.layout.activity_constraint_layout2)

        btn_start.setOnClickListener {
            // 4.准备开始动画
            TransitionManager.beginDelayedTransition(root_layout)
            mConstraintSet2.applyTo(root_layout)
        }

        btn_reset.setOnClickListener {
            val transition = AutoTransition()
            transition.duration = 500
            TransitionManager.beginDelayedTransition(root_layout, transition)
            mConstraintSet1.applyTo(root_layout)
        }

    }
}
