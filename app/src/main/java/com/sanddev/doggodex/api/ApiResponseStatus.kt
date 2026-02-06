package com.sanddev.doggodex.api

import com.sanddev.doggodex.Dog

sealed class ApiResponseStatus<T> {
    class Success<T>(val data: T): ApiResponseStatus<T>()
    class Loading<T>(): ApiResponseStatus<T>()
    class Error<T>(val messageId: Int): ApiResponseStatus<T>()
}