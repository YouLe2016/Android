package com.wyl.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

import com.wyl.android.R
import com.wyl.android.databinding.NavigationDetailFragmentBinding
import com.wyl.android.databinding.NavigationHomeFragmentBinding
import kotlinx.android.synthetic.main.navigation_detail_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var binding: NavigationDetailFragmentBinding
    private lateinit var viewModel: NavigationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.navigation_detail_fragment,
            container,
            false
        )
        viewModel = ViewModelProviders.of(
            activity!!,
            SavedStateViewModelFactory(activity!!.application, this)
        ).get(NavigationViewModel::class.java)

        binding.lifecycleOwner = this
        binding.data = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        navigator()
        button.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
//            Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_navigation2Activity)
//            Navigation.findNavController(it)
//                .navigate(R.id.action_detailFragment_to_mobile_navigation)
        }
    }

    private fun navigator() {
        val clickListener =
            Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_homeFragment)
        button.setOnClickListener(clickListener)
    }


}
