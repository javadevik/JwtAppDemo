package com.ua.jwtappdemo.security.jwt

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class JwtUser(
    private val id: Long,
    private val username: String,
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private val password: String,
    private val enabled: Boolean,
    private val lastPasswordResetDate: Long?,
    private val authorities: Set<out GrantedAuthority>?
) : UserDetails {
    override fun getAuthorities(): Set<out GrantedAuthority>? {
        return authorities
    }

    @JsonIgnore
    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isEnabled(): Boolean {
        return true
    }
}