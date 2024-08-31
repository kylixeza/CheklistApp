package com.kylix.checklistapp.data.api

import com.kylix.checklistapp.data.local.ChecklistDatastore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val dataStore: ChecklistDatastore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { dataStore.getToken() }

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }

}