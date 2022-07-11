package com.ua.jwtappdemo.dto

import com.ua.jwtappdemo.entities.UserEntity

class UserDto(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    companion object {
        fun toDto(userEntity: UserEntity): UserDto {
            return UserDto(
                userEntity.id,
                userEntity.username,
                userEntity.firstName,
                userEntity.lastName,
                userEntity.email
            )
        }
    }
}