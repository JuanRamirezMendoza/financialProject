package com.financialproject.financialproject.data.model

import com.financialproject.financialproject.data.network.FirebaseController

data class LoginModel(val email: String, val password: String) {
    private val firebaseController = FirebaseController()

    fun auth(success: () -> Unit, error: () -> Unit) {
        firebaseController.auth(this, success, error)
    }
}