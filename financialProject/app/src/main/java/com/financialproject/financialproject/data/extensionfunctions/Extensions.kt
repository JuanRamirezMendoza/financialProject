package com.financialproject.financialproject.data.extensionfunctions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.financialproject.financialproject.data.model.InOut

fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, text, length).show()
}

fun List<InOut>.count(kindOfMove: String): String {
    var incoming: Int = 0
    this.f("Incoming").forEach {
        incoming += it.price.toInt()
    }
    return incoming.toString()
}

private fun List<InOut>.f(kindOfMove: String) = filter { m -> kindOfMove == m.kindOfMove }