package com.financialproject.financialproject.ui.view

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.financialproject.financialproject.databinding.ActivityMenuViewBinding
import com.financialproject.financialproject.ui.view.fragments.Fragment1
import com.financialproject.financialproject.ui.view.fragments.Fragment2

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
