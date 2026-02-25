package com.sanddev.doggodex.auth

import com.sanddev.doggodex.models.User
import com.sanddev.doggodex.api.ApiResponseStatus
import com.sanddev.doggodex.api.DogsApi.retrofitService
import com.sanddev.doggodex.api.dto.LogInDTO
import com.sanddev.doggodex.api.dto.SignUpDTO
import com.sanddev.doggodex.api.dto.UserDTOMapper
import com.sanddev.doggodex.api.makeNetworkCall

class AuthRepository {
    suspend fun signUp(email: String, password:String, confirmationPassword: String): ApiResponseStatus<User> = makeNetworkCall {
        val signUpDTO = SignUpDTO(email, password, confirmationPassword)
        val signUpResponse = retrofitService.signUp(signUpDTO)

        if (!signUpResponse.isSuccess) {
            throw Exception(signUpResponse.message)
        }

        val userDTO = signUpResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }

    suspend fun logIn(email: String, password: String) : ApiResponseStatus<User> = makeNetworkCall {
        val loginDTO = LogInDTO(email, password)
        val loginResponse = retrofitService.logIn(loginDTO)

        if (!loginResponse.isSuccess) {
            throw Exception(loginResponse.message)
        }

        val userDTO = loginResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }
}