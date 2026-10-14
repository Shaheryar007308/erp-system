package com.example.erpsystem.data.repository

import com.example.erpsystem.data.model.LoginResponse
import com.example.erpsystem.data.remote.ApiService

class AuthRepository(private val apiService : ApiService) {


    fun loginUser(
        email : String ,
        password: String ,
        onResult: (Result<LoginResponse>) -> Unit
    ){
        val credentials = mapOf("email" to email , "password" to password)
        val call = apiService.login(credentials)
        call.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse>,
                response: retrofit2.Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        onResult(Result.success(loginResponse))
                    } else {
                        onResult(Result.failure(Exception("Empty response body")))
                    }
                } else {
                    onResult(Result.failure(Exception("Login failed with status code: ${response.code()}")))
                }
            }

            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                onResult(Result.failure(t))
            }
        })
    }

}