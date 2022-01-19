package com.financialproject.financialproject.data.model

import com.financialproject.financialproject.data.network.FirebaseController

class FragmentHomeModel() {
    private val firebaseController = FirebaseController()
    fun signOut(success: () -> Unit){
        firebaseController.signOut(success)
    }
}