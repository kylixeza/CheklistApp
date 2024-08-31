package com.kylix.checklistapp.di

import com.kylix.checklistapp.data.api.AuthApiService
import com.kylix.checklistapp.data.api.ChecklistApiService
import com.kylix.checklistapp.data.api.TokenInterceptor
import com.kylix.checklistapp.data.local.ChecklistDatastore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single { TokenInterceptor(get()) }

    single {

        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<TokenInterceptor>())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .cache(
                okhttp3.Cache(
                    androidContext().cacheDir,
                    10 * 1024 * 1024
                )
            )
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("http://94.74.86.174:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(AuthApiService::class.java) }
    single { get<Retrofit>().create(ChecklistApiService::class.java) }
}

val datastoreModule = module {
    single {
        ChecklistDatastore(androidApplication())
    }
}