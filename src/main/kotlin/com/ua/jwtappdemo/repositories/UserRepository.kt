package com.ua.jwtappdemo.repositories

import com.ua.jwtappdemo.entities.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findUserByUsername(username: String): UserEntity
    override fun findAll(): List<UserEntity>
}