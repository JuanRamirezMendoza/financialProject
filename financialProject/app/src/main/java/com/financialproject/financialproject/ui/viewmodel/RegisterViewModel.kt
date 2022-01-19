package com.financialproject.financialproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.financialproject.financialproject.data.model.RegisterModel

class RegisterViewModel : BaseViewModel(){

    val name: MutableLiveData<String> = MutableLiveData("")
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val passwordConfirm: MutableLiveData<String> = MutableLiveData("")

    fun register() {

        val name = name.value ?: ""
        val emailRegister = email.value ?: ""
        val passwordRegister = password.value ?: ""
        val passwordConfirm = password.value ?: ""
        println(emailRegister)
        println(passwordRegister)
        try {
            if (name.isEmpty()
                ||emailRegister.isEmpty()
                || passwordRegister.isEmpty()
                || passwordConfirm.isEmpty()
            ) error.value = ERROR.EMPTY_FIELDS
            else {
                val model = RegisterModel(emailRegister, passwordRegister)
                model.register({
                    navigation.value = NAVIGATION.GO_LOGIN_VIEW
                    success.value = SUCCESS.REGISTER_SUCCES
                }, {
                    error.value = ERROR.WRONG_CREDENTIALS
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}