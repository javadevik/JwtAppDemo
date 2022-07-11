package com.ua.jwtappdemo.controllers

import com.ua.jwtappdemo.dto.UserDto
import com.ua.jwtappdemo.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    val userService: UserService
) {
    @Secured("ROLE_USER")
    @GetMapping("/{userId}")
    fun getUserById(@PathVariable("userId") userId: Long): ResponseEntity<UserDto> {
        val userFound = userService.findById(userId) ?: return ResponseEntity(HttpStatus.NO_CONTENT)
        val userFoundDto = UserDto.toDto(userFound)
        return ResponseEntity(userFoundDto, HttpStatus.OK)
    }
}