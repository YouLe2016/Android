package com.wyl.android.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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
        imageView.rotation = viewModel.imageRotation

        imageView.setOnClickListener {
            viewModel.imageRotation = if (viewModel.imageRotation == 0f) 90f else 0f
            imageView.animate()
                .rotation(viewModel.imageRotation)
                .withStartAction { imageView.isClickable = false }
                .withEndAction { imageView.isClickable = true }
        }
    }


}
