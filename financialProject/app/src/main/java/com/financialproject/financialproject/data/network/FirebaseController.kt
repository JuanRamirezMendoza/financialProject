package com.financialproject.financialproject.data.network

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.financialproject.financialproject.data.model.FragmentInOutModel
import com.financialproject.financialproject.data.model.InOut
import com.financialproject.financialproject.data.model.LoginModel
import com.financialproject.financialproject.data.model.RegisterModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class FirebaseController {

    val listIn: MutableLiveData<MutableList<InOut>> = MutableLiveData(mutableListOf())
    private var instance: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
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

    fun readData() {
        db.collection("inOut").whereEqualTo("email", email)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (value == null) return@addSnapshotListener

                val inOuts = mutableListOf<InOut>()
                for (document in value) {
                    val kindOfMove = document.data["kindOfMove"].toString()
                    val concept = document.data["concept"].toString()
                    val price = document.data["price"].toString()
                    val date = document.data["date"].toString()
                    val description = document.data["description"].toString()
                    val info = document.data["info"].toString()
                    inOuts.add(
                        InOut(
                            "",
                            document.id,
                            kindOfMove,
                            concept,
                            price.replace(".",""),
                            date,
                            description,
                            info
                        )
                    )
                }
                inOuts.sortByDescending { it.date }
                listIn.value = inOuts
            }
    }
}