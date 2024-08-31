package com.kylix.checklistapp.data.api.request

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
