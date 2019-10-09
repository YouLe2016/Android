package com.wyl.android.navigation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.wyl.android.R
import kotlinx.android.synthetic.main.content_toolbar.*

import kotlinx.android.synthetic.main.toolbar_activity.*

class Navigation3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toolbar_activity)
        setSupportActionBar(toolbar)

        val controller = findNavController(R.id.fragment)
        toolbar.setupWithNavController(controller)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
