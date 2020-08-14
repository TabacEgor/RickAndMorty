package com.example.rickandmorty.data.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {
    fun createService(isDebug: Boolean): IApiService {
        val okHttpClient =
            createOkHttpClient(
                createLoggingInterceptor(
                    isDebug
                )
            )
        return createService(
            okHttpClient,
            Gson()
        )
    }

    private fun createService(okHttpClient: OkHttpClient, gson: Gson): IApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(IApiService::class.java)
    }

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(150, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if(isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}