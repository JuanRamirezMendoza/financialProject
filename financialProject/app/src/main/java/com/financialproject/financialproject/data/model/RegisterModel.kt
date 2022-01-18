package com.financialproject.financialproject.data.model

import com.financialproject.financialproject.data.network.FirebaseController

data class RegisterModel(val email: String, val password: String) {

    private val firebaseController = FirebaseController()

    fun register(success: () -> Unit, error: () -> Unit){
        firebaseController.register(this, success, error)
    }
}