package com.financialproject.financialproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.financialproject.financialproject.data.model.RegisterModel

class RegisterViewModel : BaseViewModel(){

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val passwordConfirm: MutableLiveData<String> = MutableLiveData("")

    fun register() {
        Log.e("entro","xD")
        val emailRegister = email.value ?: ""
        val passwordRegister = password.value ?: ""
        //val passwordConfirmRegister = password.value ?: ""
        println(emailRegister)
        println(passwordRegister)
        try {
            if (emailRegister.isEmpty()
                || passwordRegister.isEmpty()
                //|| passwordConfirmRegister.isEmpty()
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