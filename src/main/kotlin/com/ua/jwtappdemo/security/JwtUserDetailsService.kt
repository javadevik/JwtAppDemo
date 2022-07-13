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
        return userService.findByUsername(username)?.let(JwtUserFactory.Companion::createJwtUser)
    }

    fun loadUserByUserId(userId: Long) : UserDetails? {
        return userService.findById(userId)?.let(JwtUserFactory.Companion::createJwtUser)
    }
}