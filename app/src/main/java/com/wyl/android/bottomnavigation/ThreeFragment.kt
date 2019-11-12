package com.wyl.android.bottomnavigation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast

import com.wyl.android.R
import kotlinx.android.synthetic.main.three_fragment.*

/**
 * 平移
 *
 * [HenCoder Android开发进阶]自定义View 1-6 :属性动画 知识点:
 * 1. ViewPropertyAnimator 最简单的属性动画
 * 2. 速度模型
 */
class ThreeFragment : Fragment() {

    private lateinit var viewModel: ThreeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.three_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ThreeViewModel::class.java)

        imageView.setOnClickListener {
            Toast.makeText(it.context, "clicked me.", Toast.LENGTH_SHORT).show()
        }

        tvTranslationX.setOnClickListener {


            /*
            在调用这行代码后, 它就会每隔10 ms调用一次View.setTranslationX()方法,
            把View一点点往右挪,直到挪到目标位置, 也就是右边200像素的位置

             */
            imageView.animate()
                .translationX(200f)
//                .setDuration(300) // 默认的动画时长是300ms, 你也可以自己设置
//                .setInterpolator() // 它的中文可以叫做内插器或者叫做插值器, 默认: AccelerateDecelerateInterpolator

        }
    }

}
