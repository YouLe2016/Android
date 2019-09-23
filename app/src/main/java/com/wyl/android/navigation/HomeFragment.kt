package com.wyl.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.wyl.android.R
import kotlinx.android.synthetic.main.navigation_home_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.navigation_home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener {
            val name = "I am from HomeFragment."

            // val navController = NavHostFragment.findNavController(this)
            val navController = Navigation.findNavController(it)
            navController.navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply {
                    putString("name", name)
                }
            )
        }
    }

}
