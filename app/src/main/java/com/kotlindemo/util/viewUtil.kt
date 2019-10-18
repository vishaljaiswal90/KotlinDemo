package com.kotlindemo.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kotlindemo.R

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}
fun ProgressBar.hide() {
    visibility = View.GONE
}

@SuppressLint("ResourceAsColor")
fun View.snackBar(message : String){
    Snackbar.make(this,message,Snackbar.LENGTH_LONG).also { snackbar ->
//        snackbar.setBackgroundTint(R.color.colorPrimaryDark)
        snackbar.setAction("ok"){
            snackbar.dismiss()
        }
    }.show()
}