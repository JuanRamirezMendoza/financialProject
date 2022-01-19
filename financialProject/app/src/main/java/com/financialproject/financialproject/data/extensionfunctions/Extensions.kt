package com.financialproject.financialproject.data.extensionfunctions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}
fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, text, length).show()
}