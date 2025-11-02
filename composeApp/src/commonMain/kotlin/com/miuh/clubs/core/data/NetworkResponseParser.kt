package com.miuh.clubs.core.data

import com.miuh.clubs.core.util.Error
import com.miuh.clubs.core.util.NetworkError
import com.miuh.clubs.core.util.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class NetworkResponseParser {
    suspend inline fun <reified R> safeCall(
        execute: () -> HttpResponse
    ): Result<R, Error> {
        val response = try {
            execute()

        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            e.printStackTrace()
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(NetworkError.UNKNOWN)
        }
        return responseToResult(response)
    }

    suspend inline fun <reified R> responseToResult(
        response: HttpResponse
    ): Result<R, Error> {

        return when (response.status.value) {
            in 200..299 -> {
                Result.Success(response.body<R>())
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            409 -> Result.Error(NetworkError.CONFLICT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500 .. 599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }

    }
}