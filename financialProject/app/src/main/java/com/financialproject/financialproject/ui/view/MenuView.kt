package com.financialproject.financialproject.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.financialproject.financialproject.R
import com.financialproject.financialproject.databinding.ActivityMenuViewBinding
import com.financialproject.financialproject.ui.view.fragments.Fragment1
import com.financialproject.financialproject.ui.view.fragments.Fragment2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuView : AppCompatActivity() {

    private lateinit var binding: ActivityMenuViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // In Activity's onCreate() for instance
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment1, R.id.fragment2, R.id.fragment3))
        setupActionBarWithNavController(navController, appBarConfiguration)




        /*binding.button.setOnClickListener {
            val farm = supportFragmentManager.beginTransaction()
            farm.replace(binding.frameLayout.id, Fragment1())
            farm.commit()
        }
        binding.button2.setOnClickListener {
            val farm = supportFragmentManager.beginTransaction()
            farm.replace(binding.frameLayout.id, Fragment2())
            farm.commit()
        }
        binding.button3.setOnClickListener {

        }*/
    }
}
