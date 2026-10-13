package com.example.erpsystem.data.model

import com.google.gson.annotations.SerializedName

data class StudentResponse(
    @SerializedName("message") val message: String,
    @SerializedName("student") val student : Student
)

data class StudentListResponse(
    @SerializedName("message") val message: String,
    @SerializedName("students") val students: List<Student>
)
data class Student(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rollNo") val rollNo: String,
    @SerializedName("session") val session: String,
    @SerializedName("currentSemester") val currentSemester: Int,
    @SerializedName("cnic") val cnic: String,
    @SerializedName("department") val department: String,
    @SerializedName("photoUrl") val photoUrl: String
)
