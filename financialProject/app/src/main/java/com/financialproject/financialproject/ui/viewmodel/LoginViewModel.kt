package com.financialproject.financialproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.financialproject.financialproject.data.model.LoginModel
import com.google.android.gms.common.config.GservicesValue.value

class LoginViewModel : BaseViewModel(){

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    /*init {
        if (firebaseController.hasSession()){
            navigation.value = NAVIGATION.GO_MAIN_VIEW
        }
    }*/

    fun login() {
        val emailLogin = email.value ?: ""
        val passwordLogin = password.value ?: ""
        try {
            if (emailLogin.isEmpty()
                || passwordLogin.isEmpty()
            ) error.value = ERROR.EMPTY_FIELDS
            else {
                val model = LoginModel(emailLogin, passwordLogin)
                model.auth({
                    navigation.value = NAVIGATION.GO_MAIN_VIEW
                }, {
                    error.value = ERROR.WRONG_CREDENTIALS
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun register() {
        navigation.value = NAVIGATION.GO_REGISTER_VIEW
    }
}