package com.financialproject.financialproject.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

import android.view.Window
import androidx.activity.viewModels
import com.financialproject.financialproject.data.extensionfunctions.toast
import com.financialproject.financialproject.databinding.ActivityLoginViewBinding
import com.financialproject.financialproject.databinding.ActivityLoginViewBinding.inflate
import com.financialproject.financialproject.ui.viewmodel.ERROR
import com.financialproject.financialproject.ui.viewmodel.LoginViewModel
import com.financialproject.financialproject.ui.viewmodel.NAVIGATION
import com.financialproject.financialproject.ui.viewmodel.SUCCESS


class LoginView : AppCompatActivity() {

    private lateinit var binding: ActivityLoginViewBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelLogin = loginViewModel

        // In Activity's onCreate() for instance
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        /*binding.helloWorld.setOnClickListener{
            val intent = Intent(this, MenuView::class.java)
            startActivity(intent)
        }*/

        loginViewModel.error.observe(this, {
            when (it) {
                ERROR.EMPTY_FIELDS -> {
                    toast("No deje campos vacios")
                }
                ERROR.WRONG_CREDENTIALS -> {
                    toast("credenciales invalidas")
                }
            }
        })
        loginViewModel.success.observe(this, {
            when (it) {
                SUCCESS.LOGIN_SUCCES -> {
                    toast("login correcto")
                }
            }
        })
        loginViewModel.navigation.observe(this, {
            when (it) {
                NAVIGATION.GO_REGISTER_VIEW -> {
                    val intent = Intent(context, RegisterView::class.java)
                    context.startActivity(intent)
                }
                NAVIGATION.GO_MAIN_VIEW -> {
                    val intent = Intent(context, MenuView::class.java)
                    context.startActivity(intent)
                    finish()
                }
            }
        })
    }
}