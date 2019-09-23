package com.wyl.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

import com.wyl.android.R
import kotlinx.android.synthetic.main.navigation_detail_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.navigation_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val clickListener =
            Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_homeFragment)
        button.setOnClickListener(clickListener)

//        textView.text = if (arguments == null) {
//            "123"
//        } else {
//            arguments!!["name"]?.toString()
//        }

        textView.text = arguments?.let { it["name"] as String } ?: "123"

    }


}
