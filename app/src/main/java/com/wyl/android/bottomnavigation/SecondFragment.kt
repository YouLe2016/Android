package com.wyl.android.bottomnavigation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.wyl.android.R
import kotlinx.android.synthetic.main.second_fragment.*

/**
 * 缩放
 */
class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)

        imageView.apply {
            scaleX = viewModel.imageScale
            scaleY = viewModel.imageScale
        }

        val animatorSet = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", viewModel.imageScale),
                ObjectAnimator.ofFloat(imageView, "scaleY", viewModel.imageScale)
            )
        }

        imageView.setOnClickListener {
            if (!animatorSet.isRunning) {
                animatorSet.childAnimations.forEach {
                    (it as ObjectAnimator).setFloatValues(
                        viewModel.imageScale,
                        viewModel.imageScaleEnd()
                    )
                }
                animatorSet.start()
                viewModel.toggleScale()
            }
        }
    }

}
