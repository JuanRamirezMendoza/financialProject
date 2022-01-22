package com.financialproject.financialproject.data.model

import com.financialproject.financialproject.data.network.FirebaseController
import com.financialproject.financialproject.ui.viewmodel.SUCCESS

data class FragmentInOutModel(
    val email: String,
    val kindOfMove: String,
    val concept: String,
    val price: String,
    val date: String,
    val description: String,
    val info: String
) {
    private val firebaseController = FirebaseController()

    fun registerInOut(success: () -> Unit, error: () -> Unit){
        firebaseController.registerInOut(this, success, error)
    }
}