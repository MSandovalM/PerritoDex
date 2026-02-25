package com.sanddev.doggodex.api.dto

import com.sanddev.doggodex.models.User

class UserDTOMapper {
    fun fromUserDTOToUserDomain(userDTO: UserDTO): User {
        userDTO.apply {
            return User(
                id,
                email,
                authenticationToken
            )
        }
    }
}