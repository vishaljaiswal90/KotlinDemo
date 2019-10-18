package com.kotlindemo.data.network.responses

import com.kotlindemo.data.db.entity.User

data class AuthResponse (
  val isSuccess : Boolean?,
  val message : String?,
  val user :User?
)