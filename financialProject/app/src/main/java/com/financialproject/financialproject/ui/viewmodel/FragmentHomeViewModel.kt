package com.financialproject.financialproject.ui.viewmodel

import com.financialproject.financialproject.data.model.FragmentHomeModel

class FragmentHomeViewModel : BaseViewModel() {
    private val model = FragmentHomeModel()

    fun listInOut2() = model.listInOut1()

    fun logOut() {
        model.signOut {
            navigation.value = NAVIGATION.GO_LOGIN_VIEW
        }
    }

    fun listInOut(){
        model.listInOut()
    }

    fun sumIncoming(){
        model.listInOut1()
    }
}