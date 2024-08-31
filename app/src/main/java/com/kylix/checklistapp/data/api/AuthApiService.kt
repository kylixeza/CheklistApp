package com.kylix.checklistapp.data.api

import com.kylix.checklistapp.data.api.request.LoginRequest
import com.kylix.checklistapp.data.api.request.RegisterRequest
import com.kylix.checklistapp.data.api.response.BaseResponse
import com.kylix.checklistapp.data.api.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("register")
    suspend fun register(
        @Body body: RegisterRequest
    ): BaseResponse<Unit>

    @POST("login")
    suspend fun login(
        @Body body: LoginRequest
    ): BaseResponse<LoginResponse>
}