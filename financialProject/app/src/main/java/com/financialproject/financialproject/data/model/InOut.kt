package com.financialproject.financialproject.data.model

import java.io.Serializable

class InOut(
    var email: String = "",
    var firebaseId: String = "",
    var kindOfMove: String = "",
    var concept: String = "",
    var price: String = "",
    var date: String = "",
    var description: String = "",
    var info: String = ""
    ) : Serializable