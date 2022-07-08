package com.ua.jwtappdemo.controllers

import com.ua.jwtappdemo.dto.AuthenticationRequestDto
import com.ua.jwtappdemo.dto.AuthenticationResponseDto
import com.ua.jwtappdemo.security.jwt.JwtTokenProvider
import com.ua.jwtappdemo.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody requestDto: AuthenticationRequestDto): ResponseEntity<*> {
        val recUsername = requestDto.username
        val recPassword = requestDto.password

        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(recUsername, recPassword))

        val user = userService.findByUsername(recUsername)?: return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        val token = jwtTokenProvider.createToken(user)

        val responseDto = AuthenticationResponseDto(recUsername, token)

        return ResponseEntity(responseDto, HttpStatus.OK)
    }
}