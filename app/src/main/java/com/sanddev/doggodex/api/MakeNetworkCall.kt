package com.sanddev.doggodex.api

import com.sanddev.doggodex.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

private const val UNAUTHORIZED_CODE = 401

suspend fun <T> makeNetworkCall(call: suspend () -> T): ApiResponseStatus<T> =
    withContext(Dispatchers.IO) {
        try {
            ApiResponseStatus.Success(call())
        } catch (e: UnknownHostException) {
            ApiResponseStatus.Error(R.string.error_not_network)
        } catch (e: HttpException) {
            val errorMessage = if (e.code() == UNAUTHORIZED_CODE) {
                R.string.error_wrong_user_password
            } else R.string.error_unknown
            ApiResponseStatus.Error(errorMessage)
        } catch (e: Exception) {
            val errorMessage = when (e.message) {
                "sign_up_error" -> R.string.error_sign_up
                "sign_in_error" -> R.string.error_sign_in
                "user_already_exists" -> R.string.user_already_exists
                else -> R.string.error_unknown
            }
            ApiResponseStatus.Error(errorMessage)
        }
    }
