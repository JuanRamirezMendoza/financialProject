package com.financialproject.financialproject.ui.viewmodel

import com.financialproject.financialproject.data.model.FragmentHomeModel

class FragmentHomeViewModel : BaseViewModel() {

    private val model = FragmentHomeModel()

    fun logOut() {
        model.signOut {
            navigation.value = NAVIGATION.GO_LOGIN_VIEW
        }
    }
}