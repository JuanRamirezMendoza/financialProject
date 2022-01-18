package com.financialproject.financialproject.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.financialproject.financialproject.data.extensionfunctions.toast
import com.financialproject.financialproject.databinding.ActivityRegisterViewBinding
import com.financialproject.financialproject.databinding.ActivityRegisterViewBinding.inflate
import com.financialproject.financialproject.ui.viewmodel.ERROR
import com.financialproject.financialproject.ui.viewmodel.NAVIGATION
import com.financialproject.financialproject.ui.viewmodel.RegisterViewModel
import com.financialproject.financialproject.ui.viewmodel.SUCCESS

class RegisterView : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterViewBinding
    private val registerViewModel: RegisterViewModel by viewModels()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelRegister = registerViewModel

        // In Activity's onCreate() for instance
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        registerViewModel.error.observe(this, {
            when (it) {
                ERROR.EMPTY_FIELDS -> {
                    toast("No deje campos vacios")
                }
            }
        })

        registerViewModel.navigation.observe(this, {
            when (it) {
                NAVIGATION.GO_LOGIN_VIEW -> {
                    val intent = Intent(context, LoginView::class.java)
                    context.startActivity(intent)
                }
            }
        })

        registerViewModel.success.observe(this,{
            when (it){
                SUCCESS.REGISTER_SUCCES ->{
                    toast("Registro exitoso")
                }
            }
        })
    }
}