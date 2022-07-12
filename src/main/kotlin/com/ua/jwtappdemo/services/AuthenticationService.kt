package com.ua.jwtappdemo.services

import com.ua.jwtappdemo.dto.AuthenticationRequestDto
import com.ua.jwtappdemo.dto.AuthenticationResponseDto
import com.ua.jwtappdemo.dto.RegistrationResponseDto
import com.ua.jwtappdemo.entities.UserEntity

interface AuthenticationService {
    fun login(requestDto: AuthenticationRequestDto): AuthenticationResponseDto?
    fun registration(userEntity: UserEntity): RegistrationResponseDto
}