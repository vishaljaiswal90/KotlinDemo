package com.kotlindemo.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kotlindemo.R
import com.kotlindemo.data.db.AppDatabase
import com.kotlindemo.data.db.entity.User
import com.kotlindemo.data.network.MyApi
import com.kotlindemo.data.repositories.UserRepository
import com.kotlindemo.databinding.ActivityLoginBinding
import com.kotlindemo.ui.home.HomeActivity
import com.kotlindemo.util.hide
import com.kotlindemo.util.show
import com.kotlindemo.util.snackBar
import com.kotlindemo.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api,db)
        val factory = AuthViewModelFactory(repository)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        binding.viewModel = viewModel;
        viewModel.authListener = this

        viewModel.getLoginUser().observe(this, Observer { user ->

            if (user !=  null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags  =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }


    override fun onStarted() {
        progress_circular.show()
    }

    override fun onSuccess(user: User) {
        progress_circular.hide()

//        root_view.snackBar("${user.name} is user Logged In")
//       toast()
    }

    override fun onFailure(message: String) {
        progress_circular.hide()
        root_view.snackBar(message)
    }
}
