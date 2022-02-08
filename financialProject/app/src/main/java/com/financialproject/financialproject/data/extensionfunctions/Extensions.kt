package com.financialproject.financialproject.data.extensionfunctions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.financialproject.financialproject.data.model.InOut
import java.text.DecimalFormat

fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, text, length).show()
}

fun List<InOut>.countIncoming(): String {
    var incoming: Long = 0
    this.kindOfMoveFilter("Incoming").forEach {
        incoming += it.price.toLong()
    }
    return formatNumber(incoming.toString())
}

fun List<InOut>.countOutcome(): String {
    var outCome: Long = 0
    this.kindOfMoveFilter("Expenses").forEach {
        outCome += it.price.toLong()
    }
    return formatNumber(outCome.toString())
}

fun List<InOut>.countTotal(): String {

    val incoming = countIncoming().replace(".", "")
    val outCome = countOutcome().replace(".", "")
    val total = incoming.toLong() - outCome.toLong()
    return formatNumber(total.toString())
}

private fun List<InOut>.kindOfMoveFilter(kindOfMove: String) =
    filter { m -> kindOfMove == m.kindOfMove }

fun formatNumber(str: String): String {
    if (str.trim { it <= ' ' }.isNotEmpty()) try {
        val value = str.toDouble()
        val formatter = DecimalFormat("#,###")
        return formatter.format(value).replace(',', '.')
    } catch (e: NumberFormatException) {
        e.printStackTrace()
    }
    return str
}

fun InOut.formatPrice() = formatNumber(price)