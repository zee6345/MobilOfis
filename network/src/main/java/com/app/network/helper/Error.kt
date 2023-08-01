package com.app.network.helper

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Error {
     fun handleException(throwable: Throwable):String {
        return when (throwable) {
            is ConnectException -> {
                "no internet connection"
            }

            is SocketTimeoutException -> {
                "connection timeout"
            }

            is UnknownHostException -> {
                "failed to reached network"
            }

            is HttpException -> {
                when (throwable.code()) {
                    401 -> {
                        // HTTP 401 Unauthorized: Invalid credentials
                        "Unauthorized: Invalid credentials"
                    }
                    403 -> {
                        // HTTP 403 Forbidden: Access denied
                        "Forbidden: Access denied"
                    }
                    404 -> {
                        // HTTP 404 Not Found: Requested resource not found
                        "Not Found: Requested resource not found"
                    }
                    // Add more cases for other HTTP error codes if needed
                    else -> {
                        // Handle other HTTP error codes with a generic message
                        "Failed to connect to server"
                    }
                }
            }

            else -> {
                ""
            }
        }
    }
}