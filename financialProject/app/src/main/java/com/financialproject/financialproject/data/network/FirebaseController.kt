package com.financialproject.financialproject.data.network

import com.financialproject.financialproject.data.model.LoginModel
import com.financialproject.financialproject.data.model.RegisterModel
import com.google.firebase.auth.FirebaseAuth


class FirebaseController {

    private var instance: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(model: RegisterModel, success: () -> Unit, error: () -> Unit) {
        instance.createUserWithEmailAndPassword(model.email, model.password).addOnCompleteListener {
            if (it.isSuccessful) {
                success.invoke()
            } else {
                error.invoke()
            }
        }
    }

    fun auth(model: LoginModel, success: () -> Unit, error: () -> Unit) {
        instance.signInWithEmailAndPassword(model.email, model.password).addOnCompleteListener {
            if (it.isSuccessful) {
                success.invoke()
            } else {
                error.invoke()
            }
        }
    }
}