package com.wyl.android.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.wyl.android.R
import com.wyl.android.databinding.NavigationHomeFragmentBinding
import com.wyl.android.lifecycle.BaseFragment
import com.wyl.android.lifecycle.fLifecycle
import kotlinx.android.synthetic.main.navigation_home_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {
    private lateinit var binding: NavigationHomeFragmentBinding
    private lateinit var viewModel: NavigationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(fLifecycle, "onCreateView")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.navigation_home_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(
            activity!!,
            SavedStateViewModelFactory(requireActivity().application, this)
        ).get(NavigationViewModel::class.java)

        binding.lifecycleOwner = this
        binding.data = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        navigateWithArgs()
        button.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailFragment)
        )

    }

    private fun navigateWithArgs() {
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