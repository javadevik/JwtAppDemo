package com.ua.jwtappdemo.controllers

import com.ua.jwtappdemo.dto.AuthenticationRequestDto
import com.ua.jwtappdemo.dto.RegistrationResponseDto
import com.ua.jwtappdemo.entities.UserEntity
import com.ua.jwtappdemo.services.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/login")
    fun login(@RequestBody requestDto: AuthenticationRequestDto): ResponseEntity<*> {
        val responseDto = authenticationService.login(requestDto)
                ?: return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        return ResponseEntity(responseDto, HttpStatus.OK)
    }

    @PostMapping("/registration")
    fun registration(@RequestBody userEntity: UserEntity): ResponseEntity<RegistrationResponseDto> {
        val registrationResponse = authenticationService.registration(userEntity)
        return ResponseEntity(registrationResponse, HttpStatus.OK)
    }
}