package com.ua.jwtappdemo.controllers

import com.ua.jwtappdemo.dto.AdminDto
import com.ua.jwtappdemo.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val userService: UserService
) {
    @Secured("ROLE_ADMIN")
    @GetMapping("/users/user/{userId}")
    fun getUserById(@PathVariable("userId") userId: Long): ResponseEntity<AdminDto> {
        val userFound = userService.findById(userId) ?: return ResponseEntity(HttpStatus.NO_CONTENT)
        val userFoundDto = AdminDto.toDto(userFound)
        return ResponseEntity(userFoundDto, HttpStatus.OK)
    }
}