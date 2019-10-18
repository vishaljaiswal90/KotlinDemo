package com.kotlindemo.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.kotlindemo.data.repositories.UserRepository
import com.kotlindemo.util.ApiException
import com.kotlindemo.util.Corutine

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoginUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }
//        authListener?.onSuccess();

        Corutine.main {

            try {
                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)

                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }

        }


        /* val loginResponse = UserRepository().userLogin(email!!,password!!)

         authListener?.onSuccess(loginResponse)*/
        // success
    }
}