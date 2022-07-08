package com.ua.jwtappdemo.repositories

import com.ua.jwtappdemo.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findUserByUsername(username: String): User
}