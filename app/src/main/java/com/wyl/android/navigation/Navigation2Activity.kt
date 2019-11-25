package com.wyl.android.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wyl.android.R
import kotlinx.android.synthetic.main.navigation2_activity.*

class Navigation2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation2_activity)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            nav_view.visibility = when (destination.id) {
                R.id.navigation_home -> View.VISIBLE
                R.id.navigation_notifications -> View.VISIBLE
                R.id.navigation_dashboard -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

}
