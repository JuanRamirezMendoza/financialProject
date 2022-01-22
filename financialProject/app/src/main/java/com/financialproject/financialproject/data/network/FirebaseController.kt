package com.financialproject.financialproject.data.network

import com.financialproject.financialproject.data.model.FragmentInOutModel
import com.financialproject.financialproject.data.model.InOut
import com.financialproject.financialproject.data.model.LoginModel
import com.financialproject.financialproject.data.model.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseController {

    private var instance: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val collection = db.collection("inOut")

    fun hasSession(): Boolean {
        return instance.currentUser != null
    }

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

    fun signOut(success: () -> Unit) {
        instance.signOut()
        success.invoke()
    }

    fun registerInOut(model: FragmentInOutModel, success: () -> Unit, error: () -> Unit) {
        val inOut = InOut(
            email = model.email,
            firebaseId = "",
            kindOfMove = model.kindOfMove,
            concept = model.concept,
            price = model.price,
            date = model.date,
            description = model.description,
            info = model.info
        )
        collection.add(inOut).addOnSuccessListener {
            success.invoke()
        }.addOnFailureListener { e ->
            e.printStackTrace()
            error.invoke()
        }
    }
}