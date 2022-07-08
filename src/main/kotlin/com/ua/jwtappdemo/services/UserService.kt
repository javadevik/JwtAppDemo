package com.ua.jwtappdemo.services

import com.ua.jwtappdemo.entities.UserEntity

interface UserService {

    fun save(user: UserEntity): UserEntity

    fun findById(userId: Long): UserEntity?

    fun findByUsername(username: String): UserEntity?

    fun findAll(): List<UserEntity>

    fun update(userId: Long, user: UserEntity): UserEntity?

    fun delete(userId: Long)
}