package com.kotlindemo.ui.auth

import androidx.lifecycle.LiveData
import com.kotlindemo.data.db.entity.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user : User)
    fun onFailure(message : String)
}