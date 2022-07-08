package com.ua.jwtappdemo.security.jwt

import com.ua.jwtappdemo.entities.Role
import com.ua.jwtappdemo.entities.Status
import com.ua.jwtappdemo.entities.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import kotlin.streams.toList

class JwtUserFactory {

    companion object {
        fun createJwtUser(user: UserEntity): JwtUser {
            return JwtUser(
                user.id, user.username,
                user.firstName, user.lastName,
                user.email, user.password,
                user.status == Status.ACTIVE,
                user.dateUpdated,
                toListOfGrantedAuthorities(HashSet<Role>(user.roles))
            )
        }

        private fun toListOfGrantedAuthorities(userRoles: Set<Role>): List<GrantedAuthority> {
            return userRoles.stream().map { role -> SimpleGrantedAuthority(role.name) }.toList()
        }
    }
}