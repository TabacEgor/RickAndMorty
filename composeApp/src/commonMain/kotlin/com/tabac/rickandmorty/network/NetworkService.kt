package com.tabac.rickandmorty.network

import com.tabac.rickandmorty.utils.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkService {
    val client: HttpClient = HttpClient {
        defaultRequest {
            url(BASE_URL)
        }
        install(ContentNegotiation) {
            json()
        }
    }

    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }
}