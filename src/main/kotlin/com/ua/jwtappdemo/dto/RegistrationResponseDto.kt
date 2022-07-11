package com.ua.jwtappdemo.dto

import com.ua.jwtappdemo.entities.UserEntity

class RegistrationResponseDto(
    var id: Long,
    var username: String,
    var email: String
) {

    companion object {
        fun toDto(userEntity: UserEntity): RegistrationResponseDto {
            return RegistrationResponseDto(
                userEntity.id,
                userEntity.username,
                userEntity.email
            )
        }
    }

}