package com.ua.jwtappdemo.security

import com.ua.jwtappdemo.security.jwt.JwtUserFactory
import com.ua.jwtappdemo.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(
        private val userService: UserService
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userService.findByUsername(username) ?: return null
        return JwtUserFactory.createJwtUser(user)
    }

    fun loadUserByUserId(userId: Long) : UserDetails? {
        val user = userService.findById(userId) ?: return null
        return JwtUserFactory.createJwtUser(user)
    }
}