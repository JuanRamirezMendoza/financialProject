package com.financialproject.financialproject.ui.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.financialproject.financialproject.R
import com.financialproject.financialproject.databinding.ActivityMenuViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuView : AppCompatActivity() {

    private lateinit var binding: ActivityMenuViewBinding

    @SuppressLint("InflateParams")
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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

    }
}
