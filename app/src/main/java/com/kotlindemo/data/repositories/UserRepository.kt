package com.kotlindemo.data.repositories


import com.kotlindemo.data.db.AppDatabase
import com.kotlindemo.data.db.entity.User
import com.kotlindemo.data.network.MyApi
import com.kotlindemo.data.network.responses.AuthResponse
import com.kotlindemo.data.network.responses.SafeApiResponse

import retrofit2.Response

class UserRepository(
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiResponse() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user : User) = db.getUserDao().upSet(user)

    fun getUser() = db.getUserDao().getUser()
}