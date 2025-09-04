package com.tabac.rickandmorty.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.time.measureTime

sealed interface RequestResult<out V, out E> {
//    data object Loading : RequestResult<Nothing>

    data class Success<V>(
        val data: V,
        val status: HttpStatusCode,
        val headers: Headers,
    ) : RequestResult<V, Nothing>

    data class Error<E>(
        val message: String,
        val status: HttpStatusCode? = null,
        val isCanceled: Boolean = false,
        val cause: Throwable? = null,
        val rawBody: String? = null,
    ) : RequestResult<Nothing, E>
}
suspend inline fun <V> safeApiCall(
    crossinline block: suspend () -> V
): RequestResult<V, NetworkError> {
    return try {
        var duration = 0L
        val value = withContext(Dispatchers.IO) {
            var result: V
            duration = measureTime {
                result = block()
            }.inWholeMilliseconds
            result
        }
        RequestResult.Success(
            data = block(),
            status = HttpStatusCode.OK,
            headers = Headers.Empty,
        )
    } catch (e: Exception) {
        RequestResult.Error(
            message = e.message ?: "An unexpected error occurred",
            cause = e.cause,
        )
    }
}

suspend inline fun <reified T : Any> HttpClient.safeGet(
    url: String,
    noinline builder: HttpRequestBuilder.() -> Unit = {}
): RequestResult<T, NetworkError> {
    return safeApiCall {
        get(url, builder).body<T>()
    }
}

sealed interface NetworkError {
    // TODO replace networkerror data with this selead interface
}

//sealed class RequestResult<out T : Any>(data: T? = null, ) {
//    data object Loading : RequestResult<Nothing>()
//
//    data class Success<T : Any>(val data: T) : RequestResult<T>(data)
//
//    data class Error(val exception: Throwable) : RequestResult<Nothing>()
//}

//suspend inline fun <reified T : Any> HttpResponse.toRequestResult(): RequestResult<T> {
//    return try {
//        if (status.isSuccess()) {
//            RequestResult.Success(body<T>())
//        } else {
//            val errorText = runCatching { bodyAsText() }.getOrNull()
//            RequestResult.Error(IllegalStateException("HTTP ${status.value} ${status.description}" + (if (!errorText.isNullOrBlank()) ": $errorText" else ""))
//            )
//        }
//    } catch (t: Throwable) {
//        RequestResult.Error(t)
//    }
//}
