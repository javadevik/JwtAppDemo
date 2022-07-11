package com.ua.jwtappdemo.dto

import com.ua.jwtappdemo.entities.Role
import com.ua.jwtappdemo.entities.Status
import com.ua.jwtappdemo.entities.UserEntity
import java.util.*

class AdminDto(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val status: Status?,
    val roles: Set<Role>?,
    val dateCreated: Long?,
    val dateUpdated: Long?
) {

    companion object {
        fun toDto(userEntity: UserEntity): AdminDto {
            return AdminDto(
                userEntity.id,
                userEntity.username,
                userEntity.firstName,
                userEntity.lastName,
                userEntity.email,
                userEntity.status,
                userEntity.roles,
                userEntity.dateCreated,
                userEntity.dateUpdated
            )
        }
    }

}