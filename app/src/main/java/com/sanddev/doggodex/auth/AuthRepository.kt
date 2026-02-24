package com.sanddev.doggodex.auth

import com.sanddev.doggodex.Dog
import com.sanddev.doggodex.User
import com.sanddev.doggodex.api.ApiResponseStatus
import com.sanddev.doggodex.api.DogsApi
import com.sanddev.doggodex.api.DogsApi.retrofitService
import com.sanddev.doggodex.api.dto.DogDTOMapper
import com.sanddev.doggodex.api.dto.SignUpDTO
import com.sanddev.doggodex.api.dto.UserDTOMapper
import com.sanddev.doggodex.api.makeNetworkCall

class AuthRepository {
    suspend fun signUp(email: String, password:String, confirmationPassword: String): ApiResponseStatus<User> = makeNetworkCall {
        val signUpDTO = SignUpDTO(email, password, confirmationPassword)
        val signUpResponse = retrofitService.signUp(signUpDTO)
        val userDTO = signUpResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }
}