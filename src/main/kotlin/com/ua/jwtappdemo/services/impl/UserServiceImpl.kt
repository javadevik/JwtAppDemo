package com.ua.jwtappdemo.services.impl

import com.ua.jwtappdemo.entities.Role
import com.ua.jwtappdemo.entities.Status
import com.ua.jwtappdemo.entities.UserEntity
import com.ua.jwtappdemo.repositories.UserRepository
import com.ua.jwtappdemo.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun save(user: UserEntity): UserEntity {
        with(user) {
            password = passwordEncoder.encode(password)
            status = Status.ACTIVE
            roles = mutableSetOf(Role.ROLE_USER)
        }
        return userRepository.save(user)
    }

    override fun findById(userId: Long): UserEntity? {
        return userRepository.findByIdOrNull(userId)
    }

    override fun findByUsername(username: String): UserEntity? {
        return userRepository.findUserByUsername(username)
    }

    override fun findAll(): List<UserEntity> {
        return userRepository.findAll()
    }

    override fun update(userId: Long, user: UserEntity): UserEntity? {
        val userToUpdate = findById(userId) ?: return null
        with(userToUpdate) {
            username = user.username
            firstName = user.firstName
            lastName = user.lastName
            email = user.email
            password = passwordEncoder.encode(user.password)
            roles = user.roles
        }
        return userToUpdate
    }

    override fun delete(userId: Long) {
        userRepository.deleteById(userId)
    }
}