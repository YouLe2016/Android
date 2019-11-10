package com.wyl.android.bottomnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.wyl.android.R
import kotlinx.android.synthetic.main.activity_bottom_navigation_view.*

class BottomNavigationViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_view)

        AppBarConfiguration(bottomNavigationView.menu)

        val navController = findNavController(R.id.fragment2)
        setupActionBarWithNavController(navController) // 会有返回按钮

        setupActionBarWithNavController(
            navController,
            // 写法一(推荐):
            AppBarConfiguration(bottomNavigationView.menu)
            // 写法二:
//            AppBarConfiguration(
//                setOf(
//                    R.id.first_fragment,
//                    R.id.second_fragment,
//                    R.id.three_fragment
//                )
//            )
        )

        bottomNavigationView.setupWithNavController(navController)

    }
}
