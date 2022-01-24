package com.financialproject.financialproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.financialproject.financialproject.data.network.FirebaseController

open class BaseViewModel : ViewModel() {
    val error : MutableLiveData<ERROR> = MutableLiveData(null)
    val success : MutableLiveData<SUCCESS> = MutableLiveData(null)
    val navigation : MutableLiveData<NAVIGATION> = MutableLiveData(null)
    val firebaseController = FirebaseController()
}

enum class ERROR{
    EMPTY_FIELDS,
    WRONG_CREDENTIALS,
    COULD_NOT_ADD
}

enum class SUCCESS{
    LOGIN_SUCCES,
    REGISTER_SUCCES,
    LOG_OUT_SUCCESS
}

enum class NAVIGATION{
    GO_MAIN_VIEW,
    GO_REGISTER_VIEW,
    GO_LOGIN_VIEW,
}