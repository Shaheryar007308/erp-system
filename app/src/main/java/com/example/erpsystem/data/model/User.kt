package com.example.erpsystem.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message") val message : String ,
    @SerializedName("user") val user : UserProfile
)

data class UserProfile(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,         // "ERP_ADMIN" or "DEPT_ADMIN"
    @SerializedName("department") val department: String
)
