package com.financialproject.financialproject.data.network

import com.financialproject.financialproject.data.model.FragmentInOutModel
import com.financialproject.financialproject.data.model.InOut
import com.financialproject.financialproject.data.model.LoginModel
import com.financialproject.financialproject.data.model.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseController {

    private var instance: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val email = Firebase.auth.currentUser?.email
    private val collection = db.collection("inOut")

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
            kindOfMove = model.kindOfMove,
            concept = model.concept,
            price = model.price,
            date = model.date,
            description = model.description,
            info = model.info,
            firebaseId = ""
        )
        collection.add(inOut).addOnSuccessListener {
            success.invoke()
        }.addOnFailureListener { e ->
            e.printStackTrace()
            error.invoke()
        }
    }

    fun listInOut(success: () -> Unit) {
        db.collection("inOut").whereEqualTo("email", email).get()
            .addOnSuccessListener { documents ->
                val inOut = mutableListOf<InOut>()
                for (document in documents) {
                    val kindOfMove = document.data["kindOfMove"].toString()
                    val concept = document.data["concept"].toString()
                    val price = document.data["price"].toString()
                    val date = document.data["date"].toString()
                    val description = document.data["description"].toString()
                    val info = document.data["info"].toString()
                    val inOutVal = InOut(
                        "",
                        document.id,
                        kindOfMove,
                        concept,
                        price,
                        date,
                        description,
                        info
                    )
                    inOut.add(inOutVal)
                    success.invoke()
                }
            }
    }
}