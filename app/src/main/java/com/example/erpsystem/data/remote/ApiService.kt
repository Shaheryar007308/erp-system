package com.example.erpsystem.data.remote

import com.example.erpsystem.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/login")
    fun login(@Body credentials: Map<String , String>) : Call<LoginResponse>

    @GET("api/auth/logout")
    fun logout() : Call<Map<String , String>>

}