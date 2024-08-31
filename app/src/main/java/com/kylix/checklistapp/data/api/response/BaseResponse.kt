package com.kylix.checklistapp.data.api.response

data class BaseResponse<T>(
    val statusCode: Int,
    val message: String,
    val errorMessage: String?,
    val data: T?
)
