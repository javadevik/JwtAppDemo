package com.ua.jwtappdemo.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.ua.jwtappdemo.entities.UserEntity
import com.ua.jwtappdemo.security.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
        @Value("\${jwt.security.secret}")
        private var secret: String,
        private val clock: Clock,
        @Autowired
        private val userDetailsService: JwtUserDetailsService
) {
    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(user: UserEntity): String {
        val userId = user.id.toString()

        val nowTime = clock.millis()
        val validity = nowTime + 3600000

        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(Date(nowTime))
                .withExpiresAt(Date(validity))
                .sign(Algorithm.HMAC256(secret))
    }

    fun getAuthentication(userId: Long): Authentication? {
        val userDetails = userDetailsService.loadUserByUserId(userId) ?: return null
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.isNotEmpty() && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length)
        }
        return null
    }

    fun verifyToken(token: String?): DecodedJWT? {
        val jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build()
        return token?.let {
            try {
                jwtVerifier.verify(token)
            }catch (e: SignatureVerificationException) {
                null
            }
        }
    }
}