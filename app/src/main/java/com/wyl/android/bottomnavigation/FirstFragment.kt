package com.wyl.android.bottomnavigation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.wyl.android.R
import kotlinx.android.synthetic.main.first_fragment.*

/**
 * 旋转
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)

        imageView.setOnClickListener {
            imageView.animate().rotationBy(90f)
        }
    }


}
