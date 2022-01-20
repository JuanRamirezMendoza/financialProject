package com.financialproject.financialproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.financialproject.financialproject.data.model.FragmentInOutModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FragmenteInOutViewModel : BaseViewModel() {

    val kindOfMove: MutableLiveData<String> = MutableLiveData("")
    val concept: MutableLiveData<String> = MutableLiveData("")
    val price: MutableLiveData<String> = MutableLiveData("")
    val date: MutableLiveData<String> = MutableLiveData("")
    val description: MutableLiveData<String> = MutableLiveData("")
    val info: MutableLiveData<String> = MutableLiveData("")
    val email = Firebase.auth.currentUser?.email

    fun ok() {
        val kindOfMoveOk = kindOfMove.value ?: ""
        val concepteOk = concept.value ?: ""
        val priceOk = price.value ?: ""
        val dateOk = date.value ?: ""
        val descriptionOk = description.value ?: ""
        val infoOk = info.value ?: ""

        try {
            if (kindOfMoveOk.isEmpty()
                || concepteOk.isEmpty()
                || priceOk.isEmpty()
                || dateOk.isEmpty()
                || descriptionOk.isEmpty()
                || infoOk.isEmpty()
            ) error.value = ERROR.EMPTY_FIELDS
            else {
                val model = FragmentInOutModel(
                    kindOfMove.toString(),
                    concept.toString(),
                    price.toString(),
                    date.toString(),
                    description.toString(),
                    info.toString()
                )
                model.registerInOut({
                    success.value = SUCCESS.REGISTER_SUCCES
                }, {
                    error.value = ERROR.EMPTY_FIELDS
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}