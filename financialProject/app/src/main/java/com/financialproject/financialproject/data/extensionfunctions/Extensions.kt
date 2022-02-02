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

fun List<InOut>.countIncomming(): String {
    var incoming: Long = 0
    this.f("Incoming").forEach {
        incoming += it.price.toLong()
    }
    return incoming.toString()
}

fun List<InOut>.countOutcome(): String {
    var outCome: Long = 0
    this.f("Expenses").forEach {
        outCome +=  it.price.toLong()
    }
    return outCome.toString()
}

fun List<InOut>.countTotal(): String {


    val total = countIncomming().toLong() - countOutcome().toLong()
    return total.toString()
}

private fun List<InOut>.f(kindOfMove: String) = filter { m -> kindOfMove == m.kindOfMove }
