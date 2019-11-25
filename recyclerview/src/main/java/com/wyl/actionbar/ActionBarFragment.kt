package com.wyl.actionbar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wyl.recyclerview.R
import kotlinx.android.synthetic.main.fragment_action_bar.*

/**
 * A simple [Fragment] subclass.
 */
class ActionBarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_action_bar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        toolbar.inflateMenu(R.menu.notify_item)
    }

}
