package com.ua.jwtappdemo.services.impl

import com.ua.jwtappdemo.dto.AuthenticationRequestDto
import com.ua.jwtappdemo.dto.AuthenticationResponseDto
import com.ua.jwtappdemo.dto.RegistrationResponseDto
import com.ua.jwtappdemo.entities.UserEntity
import com.ua.jwtappdemo.security.jwt.JwtTokenProvider
import com.ua.jwtappdemo.services.AuthenticationService
import com.ua.jwtappdemo.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService
) : AuthenticationService {
    override fun login(requestDto: AuthenticationRequestDto): AuthenticationResponseDto? {
        val recUsername = requestDto.username
        val recPassword = requestDto.password

        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(recUsername, recPassword))

        val user = userService.findByUsername(recUsername)?: return null
        val token = jwtTokenProvider.createToken(user)

        return AuthenticationResponseDto(recUsername, token)
    }

    override fun registration(userEntity: UserEntity): RegistrationResponseDto {
        val userSaved = userService.save(userEntity)
        return RegistrationResponseDto.toDto(userSaved)
    }
}